package com.example.JournalApp.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    public UserRepositoryImpl userRepository;

    @Test
    public void testSaveNewUser(){
//        assertNotNull(userRepository);
        Assertions.assertNotNull(userRepository.getUsersSA());
    }

}
