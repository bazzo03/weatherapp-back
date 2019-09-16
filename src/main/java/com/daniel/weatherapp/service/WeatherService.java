package com.daniel.weatherapp.service;


import com.daniel.weatherapp.entity.WeatherEntity;
import com.daniel.weatherapp.exception.JsonClientException;
import com.daniel.weatherapp.exception.WeatherAppException;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * WeatherService
 */
public interface WeatherService {

    List<WeatherEntity> getCityWeatherUnlockedClient(String city) throws ParseException, WeatherAppException;

    List<WeatherEntity> getCityApixuWeather(String city) throws ParseException, JsonClientException, WeatherAppException;
}