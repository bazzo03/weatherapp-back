package com.daniel.weatherapp.service.impl;

import com.daniel.weatherapp.entity.WeatherEntity;
import com.daniel.weatherapp.exception.CityException;
import com.daniel.weatherapp.exception.JsonClientException;
import com.daniel.weatherapp.exception.WeatherAppException;
import com.daniel.weatherapp.service.WeatherService;
import feign.FeignException;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceImplTest {

    @Autowired
    WeatherService weatherService;

    @Test
    public void whenWeatherUnlockedCalled_thenRespondWithData() throws ParseException, WeatherAppException {
        //given
        String city = "Bogota";

        //when
        List<WeatherEntity> list = weatherService.getCityWeatherUnlockedClient(city);

        //then
        assertThat(list.size()).isEqualTo(8);
    }

    @Test (expected = WeatherAppException.class)
    public void whenWeatherUnlockedCalledWithWrongCity_thenRespondWithError() throws ParseException, WeatherAppException {
        //given
        String city = "Bogota-adews";

        //when
        List<WeatherEntity> list = weatherService.getCityWeatherUnlockedClient(city);

        //then
        assertThat(list.size()).isEqualTo(8);
    }

    @Test
    public void whenApixuWeatherCalled_thenRespondWithData() throws WeatherAppException, ParseException {
        //given
        String city = "Cali";

        //when
        List<WeatherEntity> list = weatherService.getCityApixuWeather(city);

        //then
        assertThat(list).isNotNull();
    }

    @Test
    public void whenApixuWeatherCalledWithWrongCity_thenRespondWithError() throws WeatherAppException, ParseException {
        //given
        String city = "Cadslkhfdsljkfdslkdsli";

        //when
        List<WeatherEntity> list = weatherService.getCityApixuWeather(city);

        //then
        assertThat(list).isNotNull();
    }

}
