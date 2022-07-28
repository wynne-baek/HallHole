package com.ssafy.hallhole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HallHoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HallHoleApplication.class, args);
    }

}
