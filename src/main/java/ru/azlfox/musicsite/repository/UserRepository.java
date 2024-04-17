package ru.azlfox.musicsite.repository;

import org.springframework.data.repository.CrudRepository;
import ru.azlfox.musicsite.entity.Role;
import ru.azlfox.musicsite.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для реализации базовых методов CRUD для мероприятия,
 * а также дополнительных функций по поиску
 */
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    void deleteUserById(Long id);
}
