package ru.azlfox.musicsite.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Entity
@Table(name = "Subscription")
@Data
public class Subscription {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private long id;
//    @OneToOne
//    @JoinColumn(name = "username")
//    private User authorId;
    @ManyToOne
    @JoinColumn(name = "username")
    private User subscriberId;
}
