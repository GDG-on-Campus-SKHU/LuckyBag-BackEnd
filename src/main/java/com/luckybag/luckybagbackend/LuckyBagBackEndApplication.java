package com.luckybag.luckybagbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class LuckyBagBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuckyBagBackEndApplication.class, args);
    }

}
