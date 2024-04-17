package ru.azlfox.musicsite.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.azlfox.musicsite.exception.NoDataException;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.entity.Composition;
import ru.azlfox.musicsite.service.UserService;
import ru.azlfox.musicsite.service.CompositionService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/music/user")
public class UserController {
    private final UserService userService;
    private final CompositionService compositionService;

    public UserController(UserService userService, CompositionService compositionService) {
        this.userService = userService;
        this.compositionService = compositionService;
    }


    @GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        model.addAttribute("user", user);
        List<Composition> userComposition = this.compositionService.getCompositionByAuthor(user);
        model.addAttribute("userComposition", userComposition);
        return "user_profile";
    }

}
