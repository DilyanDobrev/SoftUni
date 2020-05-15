package com.softuni.xmlcardealer.config;

import com.softuni.xmlcardealer.utils.ValidationUtil;
import com.softuni.xmlcardealer.utils.ValidationUtilImpl;
import com.softuni.xmlcardealer.utils.XmlParser;
import com.softuni.xmlcardealer.utils.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }
}
