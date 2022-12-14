package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PostRepository implements Crud {

    private final SessionFactory sessionFactory;

    public Post createOrUpdate(Post post) {
        run(session -> session.saveOrUpdate(post), sessionFactory);
        return post;
    }

    public List<Post> findAllPostFromLastDay() {
        return findAllWithParams(
                "from Post as p where created > :fTimestampNow",
                Post.class,
                Map.of("fTimestampNow", LocalDateTime.now().minusDays(1)),
                sessionFactory
        );
    }

    public List<Post> findAllPostFromCarHasPhoto() {
        return wrapRequest(session ->
                session.createQuery("select p from Post p join fetch p.car c where c.photo is not null", Post.class).list(), sessionFactory);
    }

    public List<Post> findAllPostOfCertainBrand(String brand) {
        return findAllWithParams(
                "select p from Post p join fetch p.car c where c.name like :fKey",
                Post.class,
                Map.of("fKey", brand),
                sessionFactory
        );
    }

    public List<Post> findAllOrderById() {
        return findAllWithoutParams("from Post", Post.class, sessionFactory);
    }

    public Optional<Post> findById(int id) {
        return findOne(
                "from Post where id = :fId", Post.class,
                Map.of("fId", id),
                sessionFactory
        );
    }


}
