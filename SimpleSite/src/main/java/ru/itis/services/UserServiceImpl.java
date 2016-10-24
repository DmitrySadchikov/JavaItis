package ru.itis.services;

import ru.itis.dao.UsersDao;
import ru.itis.models.User;

import java.util.List;

import static ru.itis.utils.Verifier.verifyUserExist;
import static ru.itis.utils.Verifier.verifyUserExistByToken;

public class UserServiceImpl implements UserService {

    private UsersDao usersDao;

    public UserServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public User findIdById(int id) {
        return usersDao.findId(id);
    }

    public int findIdByToken(String token) {
        return usersDao.findId(token);
    }

    public void updateUser(User user) {
        verifyUserExist(user.getId());
        this.usersDao.update(user);
    }

    public List<User> getAll() {
        return this.usersDao.getAll();
    }

    public void deleteUser(int id) {
        verifyUserExist(id);
        this.usersDao.delete(id);
    }

    public void addUser(User user) {
        this.usersDao.add(user);
    }

    public String getPassword(String login) {
        verifyUserExist(login);
        return this.usersDao.getPassword(login);
    }

    public void setToken(String login, String token) {
        verifyUserExist(login);
        this.usersDao.setToken(login, token);
    }

    public void deleteToken(String token) {
        verifyUserExistByToken(token);
        this.usersDao.deleteToken(token);
    }

    public User findUserByToken(String token) {
        return this.usersDao.findUser(token);
    }
}
