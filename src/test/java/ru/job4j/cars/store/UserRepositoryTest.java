package ru.job4j.cars.store;

import org.hamcrest.core.Is;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Test
    public void whenAddNewUser() {
        UserRepository store = new UserRepository(sf);
        User user = new User();
        user.setLogin("test1");
        store.create(user);
        User result = store.findById(user.getId()).get();
        assertThat(result.getLogin(), is(user.getLogin()));
    }

    @Test
    public void whenAddUserAndDelete() {
        UserRepository store = new UserRepository(sf);
        User user = new User();
        user.setLogin("test1");
        store.create(user);
        store.delete(user.getId());
    }

    @Test
    public void whenFindALlUser() {
        UserRepository store = new UserRepository(sf);
        User user = new User();
        User user2 = new User();
        store.create(user);
        store.create(user2);
        List<User> expected = List.of(user, user2);
        assertThat(store.findAllOrderById(), Is.is(expected));
    }

    @Test
    public void whenFindUserByLogin() {
        UserRepository store = new UserRepository(sf);
        User user = new User();
        user.setLogin("test");
        store.create(user);
        assertThat(store.findByLogin("test").get(), Is.is(user));
    }

    @Test
    public void whenCheckUserFromPasswordAndLogin() {
        UserRepository store = new UserRepository(sf);
        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        User user2 = new User();
        user2.setLogin("test");
        user2.setPassword("test");
        store.create(user);
        User result = store.findUserByEmailAndPwd(user2).get();
        assertThat(result, Is.is(user));
    }

}