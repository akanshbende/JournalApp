package com.example.JournalApp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//    @Autowired
//    private RedisTemplate<String,String> redisTemplate;

    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate redisTemplate =new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);


        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }


}
