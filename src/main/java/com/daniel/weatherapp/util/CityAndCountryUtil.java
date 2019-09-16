package com.daniel.weatherapp.util;

import com.daniel.weatherapp.entity.WeatherEntity;
import com.daniel.weatherapp.exception.CityException;
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

public class CityAndCountryUtil {

    private static Logger logger = LoggerFactory.getLogger(CityAndCountryUtil.class);

    public static Triplet<String, Double, Double> findCityData(String cityName) throws CityException, JsonClientException {

        InputStream in = CityAndCountryUtil.class.getClassLoader().getResourceAsStream("city.list.json");
        JSONParser jsonParser = new JSONParser();
        Reader reader = new InputStreamReader(in);
        Object ob = null;
        try {
            ob = jsonParser.parse(reader);
        } catch (ParseException | IOException e) {
            throw new JsonClientException("Error parsing the cities file");
        }
        JSONArray cities = (JSONArray) ob;

        for (Object city : cities) {
            JSONObject ci = (JSONObject)city;
            if (ci.get("name").toString().trim().toLowerCase().equals(cityName.trim().toLowerCase())) {
                JSONObject coord = (JSONObject) ci.get("coord");
                return Triplet.with((String)ci.get("name"), (Double)coord.get("lon"), (Double)coord.get("lat"));
            }
        }
        logger.error("The city was not found");
        throw new CityException("The city was not found");
    }

}
