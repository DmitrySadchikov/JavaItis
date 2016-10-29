package ru.itis.dao;

import ru.itis.models.User;

import java.util.List;

public interface UsersDao {

    User findId(int id);
    int findId(String token);
    List getAll();
    List carsOfUser(int id);
    void delete(int id);
    void update(User user);
    void add(User user);
    String getPassword(String login);
    void setToken(String login, String token);
    void deleteToken(String token);
    User findUser(String token);
}
