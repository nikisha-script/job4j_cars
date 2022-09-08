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
    @NonNull
    private Timestamp startAt;

    @Column(name = "endAt")
    @NonNull
    private Timestamp endAt;

}
