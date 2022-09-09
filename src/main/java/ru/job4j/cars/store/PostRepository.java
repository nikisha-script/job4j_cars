package ru.job4j.cars.store;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class PostRepository implements Crud {

    private final SessionFactory sf;

    public List<Post> findAllPostFromLastDay() {
        return query(
                "from Post as p where created > :fTimestampNow",
                Post.class,
                Map.of("fTimestampNow", LocalDateTime.now().minusDays(1)),
                sf
        );
    }

    public List<Post> findAllPostFromCarHasPhoto() {
        return command(session ->
                session.createQuery("select c from Post join fetch c.cars where c.photo != null").list(), sf);
    }

    public List<Post> findAllPostOfCertainBrand(String brand) {
        return query(
                "select c from Post join fetch c.cars where c.name like :fKey",
                Post.class,
                Map.of("fKey", brand),
                sf
        );
    }


}
