package com.daniel.weatherapp.converter;

import com.daniel.weatherapp.dto.WeatherDto;
import com.daniel.weatherapp.entity.WeatherEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class WeatherConverterToEntity implements Converter<WeatherDto, WeatherEntity> {

    @Override
    public WeatherEntity convert(WeatherDto dto) {
        return new WeatherEntity(dto.getId(), dto.getDate(), dto.getTempMinC(),
            dto.getTempMinF(), dto.getTempMaxC(), dto.getTempMaxF(), dto.getSky());
    }
}
