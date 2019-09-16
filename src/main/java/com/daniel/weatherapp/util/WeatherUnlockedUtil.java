package com.daniel.weatherapp.util;

import com.daniel.weatherapp.entity.WeatherEntity;
import com.daniel.weatherapp.exception.JsonClientException;
import org.javatuples.Triplet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherUnlockedUtil {

    private static Logger logger = LoggerFactory.getLogger(WeatherUnlockedUtil.class);

    public static List<WeatherEntity> formatWeatherUnlockedData(JSONObject jsonObject) throws JsonClientException {

        List<WeatherEntity> list = new ArrayList<>();
        JSONArray days = (JSONArray) jsonObject.get("Days");
        int k = 0; //id created for binding purposes on the frontend table
        for (Object d : days) {
            JSONObject day = (JSONObject)d;
            k++;
            JSONArray frames = (JSONArray) day.get("Timeframes");
            try {
                list.add(new WeatherEntity(k, (String)day.get("date"), (Double)day.get("temp_max_c"),
                        (Double)day.get("temp_max_f"), (Double)day.get("temp_min_c"), (Double)day.get("temp_min_f"), ((JSONObject)frames.get(0)).get("wx_desc").toString()));
            } catch (Exception e) {
                logger.error("There is an error reading the Json data");
                throw new JsonClientException("There is an error reading the Json data from client");
            }
        }
        return list;
    }

}
