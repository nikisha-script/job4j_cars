package ru.job4j.cars.store;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class EngineRepository implements Crud {

    private final SessionFactory sessionFactory;

    public Engine create(Engine engine) {
        run(session -> session.saveOrUpdate(engine), sessionFactory);
        return engine;
    }

    public List<Engine> findAllEngines() {
        return findAllWithoutParams("from Engine", Engine.class, sessionFactory);
    }

    public Optional<Engine> findEngineByName(String name) {
        return findOne(
                "from Engine where name = :fName", Engine.class,
                Map.of("fName", name),
                sessionFactory
        );
    }

}
