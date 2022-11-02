package ru.job4j.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.cars.dto.CarDto;
import ru.job4j.cars.dto.UserDto;
import ru.job4j.cars.filter.GetHttpSession;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.EngineService;
import ru.job4j.cars.service.PostService;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CarController {

    private final EngineService engineService;
    private final CarService carService;
    private final UserService userService;
    private final PostService postService;


    @GetMapping("/add")
    public String getAddCar(Model model, HttpSession httpSession) {
        GetHttpSession.getSession(model, httpSession);
        model.addAttribute("engines", engineService.findAllEngines());
        return "add";
    }

    @PostMapping("/createPost")
    public String addPost(@RequestParam (name = "name-car") String nameCar,
                          @RequestParam (name = "file") MultipartFile photo,
                          @RequestParam (name = "name-engine") String nameEngine,
                          @RequestParam (name = "name-post") String namePost,
                          HttpSession httpSession) throws IOException {
        User user = (User) httpSession.getAttribute("user");
        try {
            UserDto userDto = UserDto.builder()
                    .userName(user.getLogin())
                    .build();
            CarDto carDto = CarDto.builder()
                    .name(nameCar)
                    .photo(photo.getBytes())
                    .engineName(nameEngine)
                    .nameOfPost(namePost)
                    .build();

            carService.create(carDto, userDto);
        } catch (NoSuchElementException e) {
            return "/404";
        }

        return "redirect:/v1/cars";

    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable("id") Integer id) {
        Car car = carService.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car is not found");
        });
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(car.getPhoto().length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(car.getPhoto()));
    }

    @GetMapping("/advert/{id}")
    public String advert(Model model, @PathVariable("id") Integer id, HttpSession session) {
        GetHttpSession.getSession(model, session);
        User user = (User) session.getAttribute("user");
        Optional<User> userRsl = userService.findByLogin(user.getLogin());
        Optional<Post> postRsl = postService.findById(id);
        if (userRsl.isEmpty() || postRsl.isEmpty()) {
            return "/404";
        }
        model.addAttribute("user", userRsl.get());
        model.addAttribute("post", postRsl.get());
        return "/advert";
    }

    @GetMapping("/sold/{id}")
    public String soldCar(@PathVariable("id") Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Optional<User> userRsl = userService.findByLogin(user.getLogin());
        Optional<Post> post = postService.findById(id);
        if (userRsl.isEmpty() || post.isEmpty()) {
            return "/404";
        }
        if (post.get().getUser().equals(userRsl.get())) {
            post.get().setSold(true);
            postService.create(post.get());
        }
        return "redirect:/v1/cars";
    }

    @GetMapping("/watch-today")
    public String watch(Model model, HttpSession session) {
        GetHttpSession.getSession(model, session);
        model.addAttribute("posts", postService.findAllPostFromLastDay());
        return "/today";
    }

    @GetMapping("/has-photo")
    public String hasPhoto(Model model, HttpSession session) {
        GetHttpSession.getSession(model, session);
        model.addAttribute("posts", postService.findAllPostFromCarHasPhoto());
        return "/has_photo";
    }

    @GetMapping("/has-brand")
    public String hasBrand(Model model, HttpSession session, @RequestParam(name = "brand") String brand) {
        GetHttpSession.getSession(model, session);
        model.addAttribute("posts", postService.findAllPostOfCertainBrand(brand));
        return "/brand_car";
    }

}
