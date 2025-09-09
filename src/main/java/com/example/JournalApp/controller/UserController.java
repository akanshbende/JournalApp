package com.example.JournalApp.controller;

import com.example.JournalApp.api.responses.WeatherResponse;
import com.example.JournalApp.entity.User;
import com.example.JournalApp.repository.UserRepository;
import com.example.JournalApp.repository.UserRepositoryImpl;
import com.example.JournalApp.service.EmailService;
import com.example.JournalApp.service.UserService;
import com.example.JournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;



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


    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse =  weatherService.getWeather("Pune");

        String greeting="";

        if(weatherResponse!=null){
            greeting= " Weather of Pune feels like " + weatherResponse.getCurrent().getFeelslike();
        }


        return new ResponseEntity<>("Hi " + authentication.getName() + greeting,HttpStatus.OK);
    }



}
