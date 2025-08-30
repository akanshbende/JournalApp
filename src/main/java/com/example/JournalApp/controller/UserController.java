package com.example.JournalApp.controller;

import com.example.JournalApp.entity.User;
import com.example.JournalApp.repository.UserRepository;
import com.example.JournalApp.service.JournalEntryService;
import com.example.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User newUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();
        User userInDb= userService.findByUserName(userName);

       if(userInDb != null){
           userInDb.setUserName(newUser.getUserName());
           userInDb.setPassword(newUser.getPassword());
           userService.saveEntry(userInDb);
            return new ResponseEntity<>(userInDb,HttpStatus.ACCEPTED);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deletAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByusername(authentication.getName()); // DOUBT

        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
