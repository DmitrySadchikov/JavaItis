package ru.itis.chat.services.interfaces;


import ru.itis.chat.models.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);
    User findUserByToken(String token);
    User findUserByLogin(String login);
    Long findIdByToken(String token);
    void updateUser(User user);
    List<User> findAll();
    void deleteUser(Long id);
    void saveUser(User user);
    String findPasswordByLogin(String login);
}
