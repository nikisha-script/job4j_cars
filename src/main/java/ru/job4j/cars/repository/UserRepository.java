package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserRepository implements Crud {

    private final SessionFactory sessionFactory;

    public User create(User user) {
        run(session -> session.saveOrUpdate(user), sessionFactory);
        return user;
    }


    public Optional<User> findByLogin(String login) {
        return findOne(
                "from User where login = :fLogin", User.class,
                Map.of("fLogin", login),
                sessionFactory
        );
    }

    public Optional<User> findUserByEmailAndPassword(String login, String password) {
        return findOne(
                "from User as u where u.login = :fLogin and u.password = :fPassword", User.class,
                Map.of("fLogin", login, "fPassword", password),
                sessionFactory
        );
    }
}
