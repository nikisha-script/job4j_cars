package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.store.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository store;

    public User create(User user) {
        return store.create(user);
    }

    public void update(User user) {
        store.update(user);
    }

    public void delete(int userId) {
        store.delete(userId);
    }

    public List<User> findAllOrderById() {
        return store.findAllOrderById();
    }

    public Optional<User> findById(int id) {
        return store.findById(id);
    }

    public List<User> findByLikeLogin(String key) {
        return store.findByLikeLogin(key);
    }

    public Optional<User> findByLogin(String login) {
        return store.findByLogin(login);
    }

    public Optional<User> findUserByEmailAndPwd(User user) {
        return store.findUserByEmailAndPwd(user);
    }

}
