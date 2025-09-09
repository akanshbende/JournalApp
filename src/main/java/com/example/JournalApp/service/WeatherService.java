package com.example.JournalApp.service;

import com.example.JournalApp.cache.SimpleAppCache;
import com.example.JournalApp.api.responses.WeatherResponse;
import com.example.JournalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@Profile("dev")
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private SimpleAppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        } else{
            String finalAPI = appCache.APP_CACHE.get(SimpleAppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.API_KEY, apiKey).replace(PlaceHolders.CITY, city);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();

            if(body!=null){
                redisService.set("weather_of_" + city,body,300l);
            }
            return body;
        }


    }

}
