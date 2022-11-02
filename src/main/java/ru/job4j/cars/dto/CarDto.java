package ru.job4j.cars.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarDto {

    private String name;
    private byte[] photo;
    private String engineName;
    private String nameOfPost;

}
