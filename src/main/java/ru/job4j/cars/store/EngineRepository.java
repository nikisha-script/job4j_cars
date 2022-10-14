package ru.job4j.cars.store;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

@Repository
@AllArgsConstructor
public class EngineRepository implements Crud {

    private final SessionFactory sessionFactory;

    public Engine create(Engine engine) {
        run(session -> session.saveOrUpdate(engine), sessionFactory);
        return engine;
    }

}
