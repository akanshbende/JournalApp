package com.example.JournalApp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
     void testEmailService(){
        emailService.sendEmail("asbende29@gmail.com",
                "Spring boot backend",
                "Hi this is my first java spring boot email!!");
    }
}
