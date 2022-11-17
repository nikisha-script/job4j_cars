package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.cars.dto.CarDto;
import ru.job4j.cars.dto.UserDto;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.CarRepository;
import ru.job4j.cars.repository.EngineRepository;
import ru.job4j.cars.repository.PostRepository;
import ru.job4j.cars.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final EngineRepository engineRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public Car create(CarDto carDto, UserDto userDto) {
        Optional<User> userRsl = userRepository.findByLogin(userDto.getUserName());
        if (userRsl.isEmpty()) {
            throw new NoSuchElementException("Engine is not found");
        }

        Car car = new Car();
        car.setName(carDto.getName());
        car.setPhoto(carDto.getPhoto());
        Optional<Engine> engine = engineRepository.findEngineByName(carDto.getEngineName());
        if (engine.isEmpty()) {
            throw new NoSuchElementException("Engine is not found");
        }
        car.setEngine(engine.get());

        Car carSave = carRepository.create(car);

        Post post = new Post();
        post.setText(carDto.getNameOfPost());
        post.setCar(carSave);
        post.setCreated(LocalDateTime.now());
        post.setUser(userRsl.get());
        post.setSold(false);

        postRepository.createOrUpdate(post);

        return carSave;

    }

    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

}
