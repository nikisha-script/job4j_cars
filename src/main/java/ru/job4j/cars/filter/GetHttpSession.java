package ru.job4j.cars.filter;

import org.springframework.ui.Model;
import ru.job4j.cars.model.User;

import javax.servlet.http.HttpSession;

public class GetHttpSession {

    public static void getSession(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setLogin("user");
        }
        model.addAttribute("user", user);
    }

}
