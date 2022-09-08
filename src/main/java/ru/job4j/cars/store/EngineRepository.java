package ru.job4j.cars.store;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EngineRepository implements Crud {

    private final SessionFactory sf;

}
