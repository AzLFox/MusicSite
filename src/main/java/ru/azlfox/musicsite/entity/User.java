package ru.azlfox.musicsite.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames="username"))
@Data
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank(message = "Логин не заполнен")
    private String username;

    @NotBlank(message = "Пароль не заполнен")
    private String password;

    @NotBlank(message = "Имя не заполнен")
    private String name;

    @NotBlank(message = "Имя не заполнен")
    private String surname;

    @Column(unique = true)
    @NotBlank(message = "Электронная почта не заполнена")
    @Email(message = "Это не электронная почта")
    private String email;

//    @Enumerated(value = EnumType.STRING)
    @NotBlank(message = "Роль не заполнена")
    private String role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Composition> composition;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Like> likes;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Comment> comment;
    @OneToMany(mappedBy = "subscriberId", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Subscription> subscriptions;

}

