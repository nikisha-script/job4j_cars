package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.store.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository store;

    public User create(User user) {
        return store.create(user);
    }

    public Optional<User> findByLogin(String login) {
        return store.findByLogin(login);
    }

    public Optional<User> findUserByEmailAndPassword(String login, String password) {
        return store.findUserByEmailAndPassword(login, password);
    }

}
