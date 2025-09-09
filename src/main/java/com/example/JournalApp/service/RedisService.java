package com.example.JournalApp.service;

import com.example.JournalApp.api.responses.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper=new ObjectMapper();
            if(o==null){
                log.warn("Key not found in Redis: {}" ,key);

            }
            return mapper.readValue(o.toString(),entityClass);// serialization POJO -> JSON ? not sure
        } catch (Exception e) {
            log.error("Exception : ",e);
            return null;
        }
    }


    public void set(String key, Object o, Long ttl) {
        try {
            ObjectMapper objectMapper=new ObjectMapper();
            String jsonValue= objectMapper.writeValueAsString(o); // serialization POJO -> JSON
            redisTemplate.opsForValue().set(key,jsonValue,ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception : ",e);
        }
    }
}
