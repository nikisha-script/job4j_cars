package ru.job4j.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.filter.GetHttpSession;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.CarService;
import ru.job4j.cars.service.EngineService;
import ru.job4j.cars.service.PostService;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

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
        return "add";
    }

    @PostMapping("/createPost")
    public String addPost(@RequestParam (name = "name-car") String nameCar,
                          @RequestParam (name = "file") MultipartFile photo,
                          @RequestParam (name = "name-engine") String nameEngine,
                          @RequestParam (name = "power-engine") String powerEngine,
                          @RequestParam (name = "name-post") String namePost,
                          HttpSession httpSession) throws IOException {
        User user = (User) httpSession.getAttribute("user");
        User userRsl = userService.findByLogin(user.getLogin()).get();

        Car car = new Car();
        car.setName(nameCar);
        car.setPhoto(photo.getBytes());

        Engine engine = new Engine();
        engine.setName(nameEngine);
        engine.setPower(Integer.parseInt(powerEngine));
        Engine rsl = engineService.create(engine);

        car.setEngine(rsl);
        Car carRsl = carService.create(car);

        Post post = new Post();
        post.setText(namePost);
        post.setCar(carRsl);
        post.setCreated(LocalDateTime.now());
        post.setUser(userRsl);

        postService.create(post);

        return "redirect:/v1/cars";
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable("id") Integer id) {
        Car car = carService.findById(id).get();
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(car.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(car.getPhoto()));
    }

    @GetMapping("/advert/{id}")
    public String getSession(Model model, @PathVariable("id") Integer id, HttpSession session) {
        GetHttpSession.getSession(model, session);
        User user = (User) session.getAttribute("user");
        User userRsl = userService.findByLogin(user.getLogin()).get();
        model.addAttribute("user", userRsl);
        model.addAttribute("post", postService.findById(id).get());
        return "/advert";
    }

}
