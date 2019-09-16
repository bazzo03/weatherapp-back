package com.daniel.weatherapp.service.impl;

import com.daniel.weatherapp.client.ApixuWeatherClient;
import com.daniel.weatherapp.client.WeatherUnlockedClient;
import com.daniel.weatherapp.entity.WeatherEntity;
import com.daniel.weatherapp.exception.CityException;
import com.daniel.weatherapp.exception.JsonClientException;
import com.daniel.weatherapp.exception.WeatherAppException;
import com.daniel.weatherapp.service.WeatherService;
import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.daniel.weatherapp.util.ApixuWeatherUtil.formatApixuWeatherData;
import static com.daniel.weatherapp.util.CityAndCountryUtil.findCityData;
import static com.daniel.weatherapp.util.GeoLocationUtil.transformLatAndLonTo2DigitLocation;
import static com.daniel.weatherapp.util.WeatherUnlockedUtil.formatWeatherUnlockedData;

/**
 * UsuarioServiceImpl
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    WeatherUnlockedClient weatherUnlockedClient;

    @Autowired
    ApixuWeatherClient apixuWeatherClient;

    @Override
    public List<WeatherEntity> getCityWeatherUnlockedClient(String city) throws WeatherAppException {

        logger.info("Obtain weather for city {} with unlockedweather api", city);

        Triplet<String, Double, Double> triplet = findCityData(city);
        Double lat = triplet.getValue2();
        Double lon = triplet.getValue1();
        String string = "";
        try {
            string = weatherUnlockedClient.getCityWeather(lat, lon, "be2c4431", "0ca57c25947c31eb2904e62284641359");
        } catch (Exception e) {
            logger.error("WeatherUnlocked service unavailable ");
            throw new WeatherAppException("WeatherUnlocked service unavailable ");
        }

        JSONParser jsonParser = new JSONParser();
        Object jsonObject = null;
        try {
            jsonObject = jsonParser.parse(string);
        } catch (ParseException e) {
            logger.error("Error parson");
            throw new JsonClientException("Error parsing the json data");
        }
        return formatWeatherUnlockedData((JSONObject) jsonObject);
    }

    @Override
    public List<WeatherEntity> getCityApixuWeather(String city) throws WeatherAppException {

        logger.info("Obtain weather for city {} with apixuweather api", city);

        String string = "";
        try {
            string = apixuWeatherClient.getCityWeather(city, "40c5f00acf1145f2a0940536190809");
        } catch (Exception e) {
            logger.error("WeatherUnlocked service unavailable");
            throw new WeatherAppException("WeatherUnlocked service unavailable and no cache available either");
        }
        JSONParser jsonParser = new JSONParser();
        Object jsonObject = null;
        try {
            jsonObject = jsonParser.parse(string);
        } catch (ParseException e) {
            logger.error("Error parsing the json data");
            throw new JsonClientException("Error parsing the json data");
        }
        return formatApixuWeatherData((JSONObject) jsonObject);
    }

}