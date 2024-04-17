package ru.azlfox.musicsite.controller;

import ru.azlfox.musicsite.entity.Comment;
import ru.azlfox.musicsite.entity.Composition;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.service.CommentService;
import ru.azlfox.musicsite.service.CompositionService;
import ru.azlfox.musicsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/music")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final CompositionService compositionService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService, CompositionService compositionService) {
        this.commentService = commentService;
        this.userService = userService;
        this.compositionService = compositionService;
    }

    @PostMapping("/composition/{compositionId}/like")
    public String createComment(@PathVariable Long compositionId,
                                @RequestParam("content") String content) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getUserByName(authentication.getName());
            Composition composition = compositionService.getById(compositionId);
            LocalDate currentDate = LocalDate.now();
            Comment comment = setComment(content, Date.valueOf(currentDate), user, composition);
            commentService.addComment(comment);
            return "redirect:/music/previousPage";
    }

    private Comment setComment(String content, Date date, User author, Composition composition) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setComposition(composition);
        comment.setCreateAt(date);
        comment.setUserId(author);
        return comment;
    }
}
