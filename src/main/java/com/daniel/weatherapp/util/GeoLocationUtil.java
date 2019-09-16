package com.daniel.weatherapp.util;

import org.javatuples.Pair;
import org.javatuples.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GeoLocationUtil {

    private static Logger logger = LoggerFactory.getLogger(GeoLocationUtil.class);

    public static Pair<Double, Double> transformLatAndLonTo2DigitLocation(Double lat, Double lon) {

        BigDecimal latBigDecimal = new BigDecimal(lat).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal lonBigDecimal = new BigDecimal(lon).setScale(2, RoundingMode.HALF_EVEN);

        return Pair.with(latBigDecimal.doubleValue(), lonBigDecimal.doubleValue());
    }
}
