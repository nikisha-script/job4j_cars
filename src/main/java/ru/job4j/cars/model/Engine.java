package ru.job4j.cars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "engines")
@NoArgsConstructor
@Getter
@Setter
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull(message = "name must be non null")
    private String name;

    @Column(name = "power")
    @NotNull(message = "power must be non null")
    private int power;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Engine engine = (Engine) o;

        return id == engine.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
