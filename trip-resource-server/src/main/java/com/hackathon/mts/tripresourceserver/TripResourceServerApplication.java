package com.hackathon.mts.tripresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TripResourceServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(TripResourceServerApplication.class, args);
    }

}
