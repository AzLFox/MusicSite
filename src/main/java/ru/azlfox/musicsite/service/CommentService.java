package ru.azlfox.musicsite.service;

import ru.azlfox.musicsite.entity.Comment;
import ru.azlfox.musicsite.entity.Composition;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.exception.NoDataException;
import ru.azlfox.musicsite.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getAllComment() {
        List<Comment> locations = new ArrayList<>();
        commentRepository.findAll().forEach(locations::add);
        return locations;
    }

    public void deleteCommetnById(Long id) {
        commentRepository.deleteCommentById(id);
    }

    public Comment getById(Long id) {
        return commentRepository.findCommentById(id).orElseThrow(() -> new NoDataException("Location with id = " + id + " not found!"));
    }

    public Comment getCommentByUserAndComposition(Long userId, Composition composition) {
        return commentRepository.findCommentByUserIdAndComposition(userId,composition).orElseThrow(() -> new NoDataException("No such location!"));
    }

    public List<Comment> getAllByUser(Long userId){
        return commentRepository.findAllByUserId(userId).orElseThrow(() -> new NoDataException("No such location in database!"));
    }
    public List<Comment> getAllByComposition(Composition composition){
        return commentRepository.findAllByComposition(composition).orElseThrow(() -> new NoDataException("No such location in database!"));
    }
}
