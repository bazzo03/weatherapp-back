package com.daniel.weatherapp.util;

import com.daniel.weatherapp.entity.WeatherEntity;
import com.daniel.weatherapp.exception.JsonClientException;
import org.javatuples.Triplet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ApixuWeatherUtil {

    private static Logger logger = LoggerFactory.getLogger(ApixuWeatherUtil.class);

    public static List<WeatherEntity> formatApixuWeatherData(JSONObject jsonObject) throws JsonClientException {

        List<WeatherEntity> list = new ArrayList<>();
        JSONObject forecast = (JSONObject) jsonObject.get("forecast");
        JSONArray forecastDay = (JSONArray) forecast.get("forecastday");
        int k = 0;
        for (Object d : forecastDay) {
            k++;
            JSONObject fd = (JSONObject)d;

            String date = (String) fd.get("date");

            JSONObject day = (JSONObject) fd.get("day");

            JSONObject condition = (JSONObject) day.get("condition");

            try {
                list.add(new WeatherEntity(k, date, (Double)day.get("mintemp_c"), (Double)day.get("mintemp_f"),
                        (Double)day.get("maxtemp_c"), (Double)day.get("maxtemp_f"), (String) condition.get("text")));
            } catch (Exception e) {
                logger.error("There is an error reading the Json data");
                throw new JsonClientException("There is an error reading the Json data");
            }

        }
        return list;
    }

}
