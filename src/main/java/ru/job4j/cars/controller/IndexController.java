package ru.job4j.cars.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cars.filter.GetHttpSession;
import ru.job4j.cars.service.PostService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/v1/cars")
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping
    public String index(Model model, HttpSession session) {
        GetHttpSession.getSession(model, session);
        model.addAttribute("posts", postService.findAllOrderById());
        return "index";
    }

    @GetMapping("/index")
    public String getIndex(Model model, HttpSession session) {
        GetHttpSession.getSession(model, session);
        return "redirect:/v1/cars";
    }




}
