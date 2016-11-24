package ru.itis.chat.services.interfaces;


import ru.itis.chat.models.User;

import java.util.List;

public interface UserService {

    User findUserById(long id);
    User findUserByToken(String token);
    long findIdByToken(String token);
    void updateUser(User user);
    List<User> findAll();
    void deleteUser(long id);
    void saveUser(User user);
    String findPasswordByLogin(String login);
    void saveToken(String login, String token);
    void deleteToken(String token);
}
