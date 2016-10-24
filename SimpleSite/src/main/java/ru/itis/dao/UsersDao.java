package ru.itis.dao;

import ru.itis.models.User;
import java.util.List;

public interface UsersDao {

    User find(int id);
    int find(String token);
    List<User> getAll();
    void delete(int id);
    void update(User user);
    void add(User user);
    String getPassword(String login);
    void setToken(String login, String token);
}
