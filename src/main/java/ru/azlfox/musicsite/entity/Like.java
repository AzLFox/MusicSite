package ru.azlfox.musicsite.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Table(name = "Likes")
@Data
public class Like {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;
    @ManyToOne
    @JoinColumn(name = "username")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "title")
    private Composition workId;
}
