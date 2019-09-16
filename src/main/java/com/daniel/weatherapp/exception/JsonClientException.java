package com.daniel.weatherapp.exception;

public class JsonClientException extends WeatherAppException {

    public JsonClientException() {}

    public JsonClientException(String msg) {
        super(msg);
    }
}
