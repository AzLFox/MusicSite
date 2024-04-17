package ru.azlfox.musicsite.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ru.azlfox.musicsite.service.UserService;
@Controller
@RequestMapping("/music/authorize")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPageHtml() {
        return "login";
    }
}
