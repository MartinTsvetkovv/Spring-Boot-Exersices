package com.example.restestatesoftuni.config;

import com.example.restestatesoftuni.utils.HtmlFileReader;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public HtmlFileReader htmlFileReader(){
        return  new HtmlFileReader();
    }
}
