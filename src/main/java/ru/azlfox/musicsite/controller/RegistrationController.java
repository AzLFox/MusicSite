package ru.azlfox.musicsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.azlfox.musicsite.service.UserService;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.entity.Role;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/music/authorize")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registrationPageHtml(Model model) {
        List<Role> roles = Role.getAllRole();
        List<String> roleName = new ArrayList<>();
        for (Role role: roles){
            roleName.add(role.getRoleName());
        }
        model.addAttribute("roles", roleName);
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUserToSystem(@ModelAttribute("user") User user, Model model) {
        try {
            userService.addUser(user);
            return "redirect:login";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "Логин: " + user.getUsername() + " уже существует!");
            return "registration";
        }
    }
}
