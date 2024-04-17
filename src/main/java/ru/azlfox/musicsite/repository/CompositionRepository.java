package ru.azlfox.musicsite.repository;
import ru.azlfox.musicsite.entity.User;
import org.springframework.data.repository.CrudRepository;
import ru.azlfox.musicsite.entity.Composition;

import java.util.Date;
import java.util.List;
import java.util.Optional;
public interface CompositionRepository extends CrudRepository<Composition, Long>{
    Optional<Composition> findCompositionById(Long id);
    Optional<List<Composition>> findAllByAuthor(User author);
    Optional<List<Composition>> findAllByTitle(String title);
    void deleteCompositionById(Long id);
}
