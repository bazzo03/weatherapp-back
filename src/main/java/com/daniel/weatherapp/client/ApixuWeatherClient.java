package com.daniel.weatherapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "apixuweather",url = "http://api-cdn.apixu.com/v1/forecast.json")
public interface ApixuWeatherClient {

    @GetMapping(value = "?key={appKey}&q={city}&days=7", produces = "application/json")
    String getCityWeather(@PathVariable("city") String city, @PathVariable("appKey") String appKey);
}
