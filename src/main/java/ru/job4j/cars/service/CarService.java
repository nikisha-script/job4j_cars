package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.store.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;


    public Car create(Car car) {
        return carRepository.create(car);
    }

    public List<Car> findAllOrderById() {
        return carRepository.findAllOrderById();
    }

    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

}
