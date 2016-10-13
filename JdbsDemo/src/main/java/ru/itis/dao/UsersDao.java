package ru.itis.dao;

import ru.itis.models.User;
import java.util.List;

public interface UsersDao {

    User find(int id);
    List<User> getAll();
    void delete(int id);
    void update(User user);
    void add(User user);
}
