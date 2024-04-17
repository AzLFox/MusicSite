package ru.azlfox.musicsite.entity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "Comments")
@Data
public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "composition")
    private Composition composition;

    @NotBlank(message = "Комментарий не должен быть пустым")
    private String content;
    private Date createAt;
}
