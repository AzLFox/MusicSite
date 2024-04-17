package ru.azlfox.musicsite.tests;

import ru.azlfox.musicsite.entity.Comment;
import ru.azlfox.musicsite.entity.Composition;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.exception.NoDataException;
import ru.azlfox.musicsite.service.CommentService;
import ru.azlfox.musicsite.service.CompositionService;
import ru.azlfox.musicsite.service.UserService;
import ru.azlfox.musicsite.config.PasswordConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({CompositionService.class, UserService.class, CommentService.class, PasswordConfig.class})
public class CompositionServiceTest {
    @Autowired
    private CompositionService compositionService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @Test
    public void testGetCompositionFromDbById() {
        Composition composition = compositionService.getById(1L);
        assertThat(composition.getTitle()).isEqualTo("Запутанный мир");
    }

    @Test
    public void testGetCompositionByTitle() {
        List<Composition> compositions = compositionService.getCompositionByTitle("Однажды в лесу");
        assertThat(compositions.size()).isEqualTo(1);
    }

    @Test
    public void testGetCompositionFromDbByAuthor() {
        User author = userService.getUserByName("AzLFox");
        List<Composition> compositions = compositionService.getCompositionByAuthor(author);
        assertThat(compositions.size()).isEqualTo(3);
    }

    @Test
    public void testGetAllComposition() {
        List<Composition> events = compositionService.getAllComposition();
        assertThat(events.size()).isEqualTo(3);
        assertThat(events.get(2).getTitle()).isEqualTo("Однажды в лесу");
    }

    @Test
    public void testAddNewEventToDbThenDeleteById() {
        Composition composition = new Composition();
        composition.setAuthor(userService.getUserById(1L));
        composition.setTitle("Проверка");
        composition.setCreatedAt(new java.util.Date());
        List<Comment> comments = new ArrayList<>();
        comments.add(commentService.getById(1L));
        comments.add(commentService.getById(2L));
        composition.setComments(comments);

        compositionService.addComposition(composition);
        Composition compositionDb = compositionService.getById(4L);
        assertThat(compositionDb.getDescription()).isEqualTo(composition.getDescription());
        assertThat(compositionDb.getAuthor()).isEqualTo(composition.getAuthor());
        compositionService.deleteCompositionById(compositionDb.getId());
        assertThrows(NoDataException.class, () -> compositionService.getById(compositionDb.getId()));
    }

    @Test
    public void testDeleteLinkedCompositionById() {
        compositionService.deleteCompositionById(1L);
        assertThrows(NoDataException.class, () -> compositionService.getById(1L));
    }
}
