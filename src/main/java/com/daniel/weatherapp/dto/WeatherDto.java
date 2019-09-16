package com.daniel.weatherapp.dto;


import com.daniel.weatherapp.entity.WeatherEntity;

/**
 * WeatherDto
 */
public class WeatherDto {

    Integer id;
    String date;
    Double tempMinC;
    Double tempMinF;
    Double tempMaxC;
    Double tempMaxF;
    String sky;

    public WeatherDto() {}

    public WeatherDto(Integer id, String date, Double tempMinC, Double tempMinF, Double tempMaxC, Double tempMaxF, String sky) {
        this.id = id;
        this.date = date;
        this.tempMinC = tempMinC;
        this.tempMinF = tempMinF;
        this.tempMaxC = tempMaxC;
        this.tempMaxF = tempMaxF;
        this.sky = sky;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Double getTempMinC() {
        return tempMinC;
    }

    public Double getTempMinF() {
        return tempMinF;
    }

    public Double getTempMaxC() {
        return tempMaxC;
    }

    public Double getTempMaxF() {
        return tempMaxF;
    }

    public String getSky() {
        return sky;
    }
}