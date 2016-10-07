package ru.itis.service;

import ru.itis.dao.UsersDao;

public interface SimpleUsersService {
    boolean isRegistered(String userName, String userPassword);
    void setUsersDao(UsersDao usersDao);
}
