package ru.job4j.cars.repository;

import org.hamcrest.core.Is;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.cars.model.User;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

public class UserRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final UserRepository userRepository = new UserRepository(sf);


    @Test
    public void whenCreateUser() {
        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        Optional<User> result = userRepository.create(user);
        user.setId(1);
        assertThat(result.get().getId(), Is.is(user.getId()));
    }

    @Test
    public void whenFindUserByLogin() {
        User user = new User();
        user.setLogin("test2");
        user.setPassword("test2");
        userRepository.create(user);
        Optional<User> result = userRepository.findByLogin("test2");
        Assert.assertNotNull(result);
    }

    @Test
    public void whenFindUserByLoginAndPassword() {
        User user = new User();
        user.setLogin("test3");
        user.setPassword("test3");
        userRepository.create(user);
        Optional<User> result = userRepository.findUserByEmailAndPassword("test3", "test3");
        Assert.assertNotNull(result);
    }
}