package com.example.somoim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class SomoimApplication {

    public static void main(String[] args) {
        SpringApplication.run(SomoimApplication.class, args);
    }

}
