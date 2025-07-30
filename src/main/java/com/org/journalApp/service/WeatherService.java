package com.org.journalApp.service;

import com.org.journalApp.api.response.WeatherResponse;
import com.org.journalApp.cache.AppCache;
import com.org.journalApp.constants.Placeholders;
import com.org.journalApp.enums.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null){
            return weatherResponse;
        }else{
            //String finalAPI= API.replace("CITY",city).replace("API_KEY",apiKey);
            String finalAPI= appCache.appCache.get(Keys.WEATHER_API.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            HttpStatus statusCode = response.getStatusCode(); // we can use this status code if we want in case of success and failure
            WeatherResponse body = response.getBody();
            if(body != null){
                redisService.set("weather_of_"+ city,body,300l);
            }
            return body;
        }
    }
}
