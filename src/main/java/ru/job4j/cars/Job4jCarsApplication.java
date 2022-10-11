package ru.job4j.cars;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Job4jCarsApplication {

    @Value("${server.port:8189}")
    private int port;

    @PostConstruct
    private void printAppUrl() {
        System.out.printf("Go to: http://localhost:%d/v1/cars%n", port);
    }

    public static void main(String[] args) {
        SpringApplication.run(Job4jCarsApplication.class, args);
    }

}
