package ru.azlfox.musicsite.repository;

import ru.azlfox.musicsite.entity.Composition;
import ru.azlfox.musicsite.entity.Like;
import ru.azlfox.musicsite.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends CrudRepository<Like, Long> {
    Optional<Like> getLikeById(Long id);
    Optional<List<Like>> getAllByUserId(User userId);
    Optional<List<Like>> getAllByWorkId(Composition title);
    @Query(value = "",nativeQuery = true)
    void deleteAllById(Long id);
    void deleteLikesByUserId(User userId);

}
