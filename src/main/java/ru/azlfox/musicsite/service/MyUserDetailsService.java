package ru.azlfox.musicsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.azlfox.musicsite.entity.User;
import ru.azlfox.musicsite.repository.UserRepository;
import ru.azlfox.musicsite.exception.NoDataException;

/**
 * Класс для помощи в осуществлении авторизации пользователя
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws NoDataException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NoDataException("User not found with username: " + username));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
