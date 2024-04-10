package com.hackathone.mts.tripbookingservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfig {
    @Bean
    public Random random() {
        return new Random();
    }
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Trip-Booking-Service API docs"));
    }
}
