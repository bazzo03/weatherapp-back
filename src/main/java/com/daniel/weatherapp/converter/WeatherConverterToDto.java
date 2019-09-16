package com.daniel.weatherapp.converter;

import com.daniel.weatherapp.dto.WeatherDto;
import com.daniel.weatherapp.entity.WeatherEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherConverterToDto implements Converter<WeatherEntity, WeatherDto> {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public WeatherDto convert(WeatherEntity entity) {
        logger.info("Converting data to dto");
        return new WeatherDto(entity.getId(), entity.getDate(), entity.getTempMinC(),
                entity.getTempMinF(), entity.getTempMaxC(), entity.getTempMaxF(), entity.getSky());
    }

    public List<WeatherDto> convertList(List<WeatherEntity> entityList) {

        logger.info("Converting data to a list of dtos");
        return entityList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
