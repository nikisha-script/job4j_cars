package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "engines")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name")
    @NotBlank(message = "name must be not empty")
    @Min(value = 3, message = "name must be more than 3")
    private String name;

    @Column(name = "power")
    @NotNull(message = "power must be non null")
    private int power;

}
