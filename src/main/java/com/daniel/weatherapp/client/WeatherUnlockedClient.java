package com.daniel.weatherapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "weatherunlocked",url = "http://api.weatherunlocked.com/api/forecast")
public interface WeatherUnlockedClient {

    @GetMapping(value = "/{lat},{lon}?app_id={appId}&app_key={appKey}", produces = "application/json")
    String getCityWeather(@PathVariable("lat") Double lat, @PathVariable("lon") Double lon, @PathVariable("appId") String appId, @PathVariable("appKey") String appKey);
}
