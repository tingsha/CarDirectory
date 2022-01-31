package com.naname.carddirectory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CardDirectoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardDirectoryApplication.class, args);
    }

}
