package com.example.medkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.example.medkit",
        "com.example.medkit.config"
})
public class MedkitApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedkitApplication.class, args);
        System.out.println("Started!!!");
    }

}
