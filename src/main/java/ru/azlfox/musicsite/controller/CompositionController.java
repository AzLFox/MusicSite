package ru.azlfox.musicsite.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.azlfox.musicsite.exception.NoDataException;
import ru.azlfox.musicsite.entity.*;
import ru.azlfox.musicsite.service.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/music")
public class CompositionController {

    private final CompositionService compositionService;
    private final UserService userService;
    private final CommentService commentService;
    private final LikeService likeService;

    public CompositionController(CompositionService compositionService, UserService userService, CommentService commentService, LikeService likeService) {
        this.compositionService = compositionService;
        this.userService = userService;
        this.commentService = commentService;
        this.likeService = likeService;
    }


    @GetMapping("/home")
    public String getMainPage(Model model) {
        List<Composition> compositions = compositionService.getAllComposition();
        model.addAttribute("compositions", compositions);
        return "home";
    }


    @GetMapping("/previousPage")
    public String getPreviousPage(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (referer != null) {
            return "redirect:" + referer;
        }
        return "redirect:home";
    }

    @GetMapping("/composition/{compositionId}")
    public String showCompositionDetailsPage(@PathVariable Long compositionId, Model model) {
        try {
            Composition composition = compositionService.getById(compositionId);
            List<Comment> comments = commentService.getAllByComposition(composition);
            List<Like> likes = likeService.getAllByComposition(composition);
            model.addAttribute("composition", composition);
            model.addAttribute("comments", comments);
            model.addAttribute("size", String.valueOf(likes.size()));
            model.addAttribute(new Comment());
            model.addAttribute("content", "");
            model.addAttribute("compositionId", "");
            return "composition";
        } catch (NoDataException e) {
            return "redirect:/previousPage";
        }
    }

    @GetMapping("/create")
    public String showCreateCompositionForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        if (user.getRole().equals("Композитор")) {
            model.addAttribute(new Composition());
            model.addAttribute("title", "");
            model.addAttribute("description", "");
            model.addAttribute("content", "");
            return "create_composition";
        } else {
            return "redirect:previousPage";
        }
    }

    @PostMapping("/create")
    public String createComposition(@RequestParam("title") String title,
                                    @RequestParam("description") String description,
                                    @RequestParam("content") String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        LocalDate currentDate = LocalDate.now();
        Composition composition = setComposition(title, description, content, Date.valueOf(currentDate), user);
        compositionService.addComposition(composition);

        return "redirect:/music/home";
    }

//    private void setModelSubscribedAttribute(Model model, User user, Composition composition) {
//        try {
//            participantService.getByUserAndEvent(user, event);
//            model.addAttribute("subscribed", true);
//        } catch (NoDataException e) {
//            model.addAttribute("subscribed", false);
//        }
//    }

    private Composition setComposition(String title, String description, String content, Date date, User author) {
        Composition composition = new Composition();
        composition.setTitle(title);
        composition.setDescription(description);
        composition.setContent(content);
        composition.setCreatedAt(date);
        composition.setAuthor(author);

        return composition;
    }

}
