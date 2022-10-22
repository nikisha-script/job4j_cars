package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository implements Crud {

    private final SessionFactory sessionFactory;

    public Car create(Car car) {
        run(session -> session.saveOrUpdate(car), sessionFactory);
        return car;
    }


    public Optional<Car> findById(int id) {
        return findOne(
                "from Car where id = :fId", Car.class,
                Map.of("fId", id),
                sessionFactory
        );
    }


}
