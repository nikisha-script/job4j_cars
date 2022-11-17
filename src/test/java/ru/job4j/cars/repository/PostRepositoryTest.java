package ru.job4j.cars.repository;

import org.hamcrest.core.Is;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

public class PostRepositoryTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    private final PostRepository postRepository = new PostRepository(sf);
    private final UserRepository userRepository = new UserRepository(sf);
    private final CarRepository carRepository = new CarRepository(sf);


    @Test
    public void whenCreatePost() {
        Post post = new Post();
        post.setText("test");
        post.setCreated(LocalDateTime.now());
        User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        Optional<User> resultUser = userRepository.create(user);
        post.setUser(resultUser.get());
        Car car = new Car();
        Engine engine = new Engine();
        engine.setName("test engine");
        engine.setPower(150);
        car.setName("test");
        car.setPhoto(new byte[1]);
        car.setEngine(engine);
        Car resultCar = carRepository.create(car);
        post.setCar(resultCar);
        post.setSold(false);
        Post postSave = postRepository.createOrUpdate(post);
        post.setId(1);
        assertThat(postSave.getId(), Is.is(post.getId()));
    }

    @Test
    public void whenFindAllPostFromLastDay() {
        Post post = new Post();
        post.setText("test2");
        post.setCreated(LocalDateTime.now());
        User user = new User();
        user.setLogin("test2");
        user.setPassword("test2");
        Optional<User> resultUser = userRepository.create(user);
        post.setUser(resultUser.get());
        Car car = new Car();
        Engine engine = new Engine();
        engine.setName("test2 engine");
        engine.setPower(150);
        car.setName("test2");
        car.setPhoto(new byte[1]);
        car.setEngine(engine);
        Car resultCar = carRepository.create(car);
        post.setCar(resultCar);
        post.setSold(false);
        Post postSave = postRepository.createOrUpdate(post);
        List<Post> expected = List.of(postSave);
        assertThat(expected, Is.is(postRepository.findAllPostFromLastDay()));
    }

    @Test
    public void whenFindAllPostCarHasPhoto() {
        Post post = new Post();
        post.setText("test3");
        post.setCreated(LocalDateTime.now());
        User user = new User();
        user.setLogin("test3");
        user.setPassword("test3");
        Optional<User> resultUser = userRepository.create(user);
        post.setUser(resultUser.get());
        Car car = new Car();
        Engine engine = new Engine();
        engine.setName("test3 engine");
        engine.setPower(150);
        car.setName("test2");
        car.setPhoto(new byte[1]);
        car.setEngine(engine);
        Car resultCar = carRepository.create(car);
        post.setCar(resultCar);
        post.setSold(false);
        Post postSave = postRepository.createOrUpdate(post);
        List<Post> expected = List.of(postSave);
        assertThat(expected, Is.is(postRepository.findAllPostFromCarHasPhoto()));
    }

    @Test
    public void whenFindAllPostOfCertainBrand() {
        Post post = new Post();
        post.setText("test4");
        post.setCreated(LocalDateTime.now());
        User user = new User();
        user.setLogin("test4");
        user.setPassword("test4");
        Optional<User> resultUser = userRepository.create(user);
        post.setUser(resultUser.get());
        Car car = new Car();
        Engine engine = new Engine();
        engine.setName("test4 engine");
        engine.setPower(150);
        car.setName("test4");
        car.setPhoto(new byte[1]);
        car.setEngine(engine);
        Car resultCar = carRepository.create(car);
        post.setCar(resultCar);
        post.setSold(false);
        Post postSave = postRepository.createOrUpdate(post);
        List<Post> expected = List.of(postSave);
        assertThat(expected, Is.is(postRepository.findAllPostOfCertainBrand("test4")));
    }

    @Test
    public void whenFindPostById() {
        Post post = new Post();
        post.setText("test5");
        post.setCreated(LocalDateTime.now());
        User user = new User();
        user.setLogin("test5");
        user.setPassword("test5");
        Optional<User> resultUser = userRepository.create(user);
        post.setUser(resultUser.get());
        Car car = new Car();
        Engine engine = new Engine();
        engine.setName("test5 engine");
        engine.setPower(150);
        car.setName("test5");
        car.setPhoto(new byte[1]);
        car.setEngine(engine);
        Car resultCar = carRepository.create(car);
        post.setCar(resultCar);
        post.setSold(false);
        Post postSave = postRepository.createOrUpdate(post);
        Optional<Post> expected = postRepository.findById(postSave.getId());
        Assert.assertNotNull(expected.get());
    }

}