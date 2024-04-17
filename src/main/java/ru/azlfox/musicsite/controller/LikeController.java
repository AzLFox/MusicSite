package ru.azlfox.musicsite.controller;

import ru.azlfox.musicsite.entity.Composition;
import ru.azlfox.musicsite.entity.Like;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.service.CompositionService;
import ru.azlfox.musicsite.service.LikeService;
import ru.azlfox.musicsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/music")
public class LikeController {
    private final LikeService likeService;
    private final UserService userService;
    private final CompositionService compositionService;

    @Autowired
    public LikeController(LikeService likeService, UserService userService, CompositionService compositionService){
        this.likeService = likeService;
        this.compositionService= compositionService;
        this.userService = userService;
    }
    @Transactional
    @PostMapping("/composition/like/{compositionId}")
    public String addLike(@PathVariable Long compositionId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());
        Composition composition = compositionService.getById(compositionId);
        boolean permission = true;
        likeService.deleteLikeByUserId(user);
        for (Like like: composition.getLikes()){
            if (like.getUserId().getId() == user.getId()) {
                permission = false;
                likeService.deleteLikeById(like.getId());
                break;
            }
        }
        if(permission){
            Like like = setLike(user, composition);
            likeService.addLike(like);
        }
        return "redirect:/music/previousPage";
    }

    private Like setLike(User userId, Composition compositionId){
        Like like = new Like();
        like.setUserId(userId);
        like.setWorkId(compositionId);
        return like;
    }
}
