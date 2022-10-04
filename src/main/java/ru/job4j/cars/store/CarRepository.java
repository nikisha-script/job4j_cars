package ru.job4j.cars.store;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepository implements Crud {

    private final SessionFactory sf;

    public Car create(Car car) {
        run(session -> session.saveOrUpdate(car), sf);
        return car;
    }

    public List<Car> findAllOrderById() {
        return query("from Car", Car.class, sf);
    }

    public Optional<Car> findById(int id) {
        return optional(
                "from Car where id = :fId", Car.class,
                Map.of("fId", id),
                sf
        );
    }


}
