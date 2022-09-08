package ru.job4j.cars.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "history_owner")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class OwnerHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "startAt")
    private Timestamp startAt;

    @Column(name = "endAt")
    private Timestamp endAt;

}
