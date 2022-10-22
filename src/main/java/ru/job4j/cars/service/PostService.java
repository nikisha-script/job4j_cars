package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post create(Post post) {
        return postRepository.create(post);
    }

    public List<Post> findAllPostFromLastDay() {
        return postRepository.findAllPostFromLastDay();
    }

    public List<Post> findAllPostFromCarHasPhoto() {
        return postRepository.findAllPostFromCarHasPhoto();
    }

    public List<Post> findAllPostOfCertainBrand(String brand) {
        return postRepository.findAllPostOfCertainBrand(brand);
    }

    public List<Post> findAllOrderById() {
        return postRepository.findAllOrderById();
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

}
