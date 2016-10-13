package ru.itis.services;

import ru.itis.models.User;

public interface UserService {

    User findUserById(int id);
    void updateUser(User owner);
}
