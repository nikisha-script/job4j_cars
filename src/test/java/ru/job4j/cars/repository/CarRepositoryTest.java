package ru.job4j.cars.repository;

import org.hamcrest.core.Is;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

public class CarRepositoryTest {


    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final CarRepository carRepository = new CarRepository(sf);


    @Test
    public void whenCreateCar() {
        Car car = new Car();
        Engine engine = new Engine();
        engine.setName("test engine");
        engine.setPower(150);
        car.setName("test");
        car.setPhoto(new byte[1]);
        car.setEngine(engine);
        Car result = carRepository.create(car);
        car.setId(1);
        assertThat(result.getId(), Is.is(car.getId()));
    }

    @Test
    public void whenFindCarById() {
        Car car = new Car();
        Engine engine = new Engine();
        engine.setName("test2 engine");
        engine.setPower(150);
        car.setName("test2");
        car.setPhoto(new byte[1]);
        car.setEngine(engine);
        Car saveCar = carRepository.create(car);
        Optional<Car> expected = carRepository.findById(saveCar.getId());
        Assert.assertNotNull(expected.get());
    }

}