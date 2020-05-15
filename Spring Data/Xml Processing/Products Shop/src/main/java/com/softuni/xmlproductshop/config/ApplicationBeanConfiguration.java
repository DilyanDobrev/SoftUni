package com.softuni.xmlproductshop.config;

import com.softuni.xmlproductshop.utils.ValidationUtil;
import com.softuni.xmlproductshop.utils.ValidationUtilImpl;
import com.softuni.xmlproductshop.utils.XmlParser;
import com.softuni.xmlproductshop.utils.XmlParserImpl;
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
