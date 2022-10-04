package ru.job4j.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Job4jCarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Job4jCarsApplication.class, args);
        System.out.println("Go to: http://localhost:8189/v1/cars");
    }

}
