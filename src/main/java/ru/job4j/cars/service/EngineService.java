package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.EngineRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EngineService {

    private final EngineRepository engineRepository;

    public Engine create(Engine engine) {
        return engineRepository.create(engine);
    }

    public List<Engine> findAllEngines() {
        return engineRepository.findAllEngines();
    }

    public Optional<Engine> findEngineByName(String name) {
        return engineRepository.findEngineByName(name);
    }

}
