package ru.itis.services;

import ru.itis.models.User;

import java.util.List;

public interface UserService {

    User findUserById(int id);
    void updateUser(User user);

    List<User> getAll();
    void deleteUser(int id);
    void addUser(User user);
}
