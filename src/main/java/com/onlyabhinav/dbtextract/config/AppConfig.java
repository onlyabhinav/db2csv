package com.onlyabhinav.dbtextract.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@Configuration
public class AppConfig {

    @Value("${app.csv.delimiter}")
    private char delimiter;

    
}
