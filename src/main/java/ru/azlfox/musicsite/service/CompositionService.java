package ru.azlfox.musicsite.service;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import ru.azlfox.musicsite.entity.Composition;
import ru.azlfox.musicsite.repository.CompositionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CompositionService {
    private final CompositionRepository compositionRepository;

    @Autowired
    public CompositionService(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    public void addComposition(Composition composition) {
        compositionRepository.save(composition);
    }

    public void updateComposition(Composition composition) {
        compositionRepository.save(composition);
    }

    public void deleteCompositionById(Long id) {
        compositionRepository.deleteCompositionById(id);
    }

    public Composition getById(Long id) {
        return compositionRepository.findCompositionById(id).orElseThrow(() -> new NoDataException("Composition with id = " + id + " not found!"));
    }

    public List<Composition> getCompositionByTitle(String title) {
        return compositionRepository.findAllByTitle(title).orElseThrow(() -> new NoDataException("No such composition with name " + title + "!"));
    }

    public List<Composition> getAllComposition() {
        List<Composition> compositions = new ArrayList<>();
        compositionRepository.findAll().forEach(compositions::add);
        return compositions;
    }

    public List<Composition> getCompositionByAuthor(User author) {
        return compositionRepository.findAllByAuthor(author).orElseThrow(() -> new NoDataException("No such compositions in this location!"));
    }

}
