package ru.itis.services;

import ru.itis.dao.UsersDao;
import ru.itis.models.User;

import static ru.itis.utils.Verifier.verifyUserExist;

public class UserServiceImpl implements UserService {

    private UsersDao usersDao;

    public UserServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }
    public User findUserById(int id) {
        return usersDao.find(id);
    }

    public void updateUser(User user) {
        verifyUserExist(user.getId());
        this.usersDao.update(user);
    }
}
