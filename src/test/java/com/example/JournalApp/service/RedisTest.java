package com.example.JournalApp.service;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Disabled
    @Test
    void redisTemplateTest(){
        // Set value
        redisTemplate.opsForValue().set("email", "gmail@gmail.com");

        // Get value
        String value = redisTemplate.opsForValue().get("email");

        // Print to console
        System.out.println("Value from Redis: " + value);

        // Optional: Add assertion for unit test
        assert value.equals("gmail@gmail.com");
    }


}
