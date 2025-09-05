package com.example.JournalApp.controller;

import com.example.JournalApp.cache.SimpleAppCache;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.repository.UserRepositoryImpl;
import com.example.JournalApp.service.EmailService;
import com.example.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private SimpleAppCache appCache;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepositorylImpl;

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> all = userService.getAll();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User user){
       userService.saveAdmin(user);
    }


    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }

    @GetMapping("/sent-email")
    public ResponseEntity<?> sentSAEmail() {
        List<User> usersSA = userRepositorylImpl.getUsersSA();

        if (!usersSA.isEmpty()) {
            for (User user : usersSA) {
                boolean emailSent = emailService.sendEmail(
                        user.getEmail(),
                        "Spring boot backend",
                        "Hi this is my first java spring boot emailer!!");

                if(emailSent){
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
