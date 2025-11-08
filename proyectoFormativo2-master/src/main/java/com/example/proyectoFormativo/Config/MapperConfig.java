package com.example.proyectoFormativo.Config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        // ajustes útiles (opcionales)
        mapper.getConfiguration()
                .setSkipNullEnabled(true)             // no sobreescribe con nulls
                .setMatchingStrategy(MatchingStrategies.STANDARD); // o STRICT
        return mapper;
    }
}