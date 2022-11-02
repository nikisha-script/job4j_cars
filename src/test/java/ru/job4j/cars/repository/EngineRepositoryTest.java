package ru.job4j.cars.repository;

import org.hamcrest.core.Is;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

public class EngineRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final EngineRepository engineRepository = new EngineRepository(sf);


    @Test
    public void whenCreateEngine() {
        Engine engine = new Engine();
        engine.setName("test engine");
        engine.setPower(150);
        Engine result = engineRepository.create(engine);
        engine.setId(1);
        assertThat(result.getId(), Is.is(engine.getId()));
    }

    @Test
    public void whenFindEngineByName() {
        Engine engine = new Engine();
        engine.setName("test2 engine");
        engine.setPower(150);
        Engine saveEngine = engineRepository.create(engine);
        Optional<Engine> expected = engineRepository.findEngineByName(engine.getName());
        Assert.assertNotNull(expected.get());
    }

    @Test
    public void whenFindAllEngine() {
        Engine engine = new Engine();
        engine.setName("test3 engine");
        engine.setPower(150);
        Engine second = new Engine();
        second.setName("test4 engine");
        second.setPower(150);
        Engine three = new Engine();
        three.setName("test5 engine");
        three.setPower(150);
        Engine saveEngine = engineRepository.create(engine);
        Engine saveSecond = engineRepository.create(second);
        Engine saveThree = engineRepository.create(three);
        List<Engine> expected = List.of(saveEngine, saveSecond, saveThree);
        assertThat(expected, Is.is(engineRepository.findAllEngines()));
    }

}