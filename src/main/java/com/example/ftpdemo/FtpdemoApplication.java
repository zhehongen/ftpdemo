package com.example.ftpdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.ftpdemo.**")
public class FtpdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FtpdemoApplication.class, args);
    }

}
