package com.example.MoneyMind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MoneyMindApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyMindApplication.class, args);
    }

}
