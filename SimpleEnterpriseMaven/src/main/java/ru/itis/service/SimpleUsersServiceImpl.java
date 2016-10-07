package ru.itis.service;

import ru.itis.dao.UsersDao;
import ru.itis.models.User;

import java.util.List;

public class SimpleUsersServiceImpl implements SimpleUsersService {

    private UsersDao usersDao;

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public boolean isRegistered(String userName, String userPassword) {
        List<User> registeredUsers = usersDao.getAll();

        for (User user : registeredUsers) {
            if (user.getName().equals(userName) &&
                    user.getPassword().equals(userPassword)) {
                return true;
            }
        }

        return false;
    }
}
