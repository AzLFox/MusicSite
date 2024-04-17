package ru.azlfox.musicsite.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ru.azlfox.musicsite.entity.User;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Compositions")
@Data
public class Composition {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User author;

    @NotBlank(message = "Название не заполнено")
    private String title;

    private String description;

    @NotBlank(message = "Отсутствует текст произведения")
    private String content;

    private Date createdAt;

    @OneToMany(mappedBy = "workId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Like> likes;
    @OneToMany(mappedBy = "composition", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comments;

}
