package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cars")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "name")
    @NotNull(message = "this is field must be no null")
    private String name;

    @Column(name = "photo")
    @NotNull(message = "this is field must be no null")
    private byte[] photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;


}
