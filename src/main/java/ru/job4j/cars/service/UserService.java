package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.filter.Md5PasswordEncrypter;
import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository store;
    private final Md5PasswordEncrypter encrypter;

    public Optional<User> create(User user) {
        user.setPassword(encrypter.passwordEncryption(user.getPassword()));
        return store.create(user);
    }

    public Optional<User> findByLogin(String login) {
        return store.findByLogin(login);
    }

    public Optional<User> findUserByEmailAndPassword(User user) {
        user.setPassword(encrypter.passwordEncryption(user.getPassword()));
        return store.findUserByEmailAndPassword(user.getLogin(), user.getPassword());
    }

}
