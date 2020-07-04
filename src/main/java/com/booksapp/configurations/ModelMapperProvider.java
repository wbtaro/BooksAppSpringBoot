package com.booksapp.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperProvider {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
