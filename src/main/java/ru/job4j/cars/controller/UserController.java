package ru.job4j.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

        private final UserService service;

        @GetMapping("/registration")
        public String registration(Model model, HttpSession session,
                                   @RequestParam(name = "fail", required = false) Boolean fail) {
                User user = (User) session.getAttribute("user");
                model.addAttribute("user", user);
                model.addAttribute("fail", fail != null);
                return "registration";
        }

        @PostMapping("/registration")
        public String registration(@ModelAttribute User user, HttpServletRequest request) {
                Optional<User> temp = service.create(user);
                if (temp.isEmpty()) {
                        return "redirect:/registration?fail=true";
                }
                HttpSession session = request.getSession();
                session.setAttribute("user", temp);
                return "redirect:/v1/cars";
        }

        @GetMapping("/loginPage")
        public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail) {
                model.addAttribute("fail", fail != null);
                return "login";
        }

        @PostMapping("/login")
        public String login(@ModelAttribute User user, HttpServletRequest request) {
                Optional<User> userDb = service.findUserByEmailAndPassword(user);
                if (userDb.isEmpty()) {
                        return "redirect:/login?fail=true";
                }
                HttpSession session = request.getSession();
                session.setAttribute("user", userDb.get());
                return "redirect:/v1/cars";
        }

        @GetMapping("/logout")
        public String logOut(HttpSession session) {
                session.invalidate();
                return "redirect:/v1/cars";
        }

}
