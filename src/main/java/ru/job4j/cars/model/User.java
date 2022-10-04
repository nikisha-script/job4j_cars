package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "auto_users")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "login")
    @NotBlank(message = "login must be not empty")
    @Min(value = 3, message = "login must be more than 3")
    private String login;

    @Column(name = "password")
    @NotBlank(message = "password must be not empty")
    @Min(value = 3, message = "password must be more than 3")
    private String password;


}
