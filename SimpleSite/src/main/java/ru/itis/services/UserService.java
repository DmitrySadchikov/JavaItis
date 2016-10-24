package ru.itis.services;

import ru.itis.models.User;

import java.util.List;

public interface UserService {

    User findIdById(int id);
    User findUserByToken(String token);
    int findIdByToken(String token);
    void updateUser(User user);
    List<User> getAll();
    void deleteUser(int id);
    void addUser(User user);
    String getPassword(String login);
    void setToken(String login, String token);
    void deleteToken(String token);
}
