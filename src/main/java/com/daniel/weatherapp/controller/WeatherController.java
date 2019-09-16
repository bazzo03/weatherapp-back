package com.daniel.weatherapp.controller;

import com.daniel.weatherapp.converter.WeatherConverterToDto;
import com.daniel.weatherapp.dto.WeatherDto;
import com.daniel.weatherapp.entity.WeatherEntity;
import com.daniel.weatherapp.service.WeatherService;
import org.javatuples.Triplet;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.ArrayList;
import java.util.List;

import static com.daniel.weatherapp.util.CityAndCountryUtil.findCityData;


/**
 * WeatherController
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class WeatherController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherConverterToDto weatherConverterToDto;

    @GetMapping("weather/{cityName}{apiServer}")
    public WebAsyncTask<ResponseEntity<List<WeatherDto>>> getWeatherUnlocked(
            @PathVariable("cityName") String cityName,
            @RequestParam String apiServer) {

        logger.info("Request to query weather for city {} with api {}", cityName, apiServer);

        if (apiServer.equals("weatherUnlocked")) {
            return new WebAsyncTask<ResponseEntity<List<WeatherDto>>>(10000, () -> {

                return new ResponseEntity(weatherConverterToDto.convertList(weatherService.getCityWeatherUnlockedClient(cityName)), HttpStatus.OK);
            });
        } else if (apiServer.equals("apixuWeather")) {
            return new WebAsyncTask<ResponseEntity<List<WeatherDto>>>(10000, () -> new ResponseEntity(weatherConverterToDto.convertList(weatherService.getCityApixuWeather(cityName)), HttpStatus.OK));
        } else {
            return new WebAsyncTask<ResponseEntity<List<WeatherDto>>>(10000, () -> new ResponseEntity(new ArrayList<WeatherDto>(), HttpStatus.BAD_REQUEST));
        }
    }


}