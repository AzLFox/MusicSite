package ru.azlfox.musicsite.repository;

import ru.azlfox.musicsite.entity.Composition;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findCommentById(Long id);
    Optional<Comment> findCommentByUserIdAndComposition(Long userId, Composition composition);
    Optional<List<Comment>> findAllByComposition(Composition composition);
    Optional<List<Comment>> findAllByUserId(Long userId);
    void deleteCommentById(Long id);
}
