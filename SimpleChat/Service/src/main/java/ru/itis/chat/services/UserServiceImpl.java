package ru.itis.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.dao.UsersDao;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.UserService;

import java.util.List;

import static ru.itis.chat.validators.UserValidator.verifyUserExistById;
import static ru.itis.chat.validators.UserValidator.verifyUserExistByLogin;
import static ru.itis.chat.validators.UserValidator.verifyUserExistByToken;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDao usersDao;

    public User findUserById(long id) {
        return usersDao.findUserById(id);
    }

    public long findIdByToken(String token) {
        return usersDao.findIdByToken(token);
    }

    public void updateUser(User user) {
        verifyUserExistById(user.getId());
        this.usersDao.update(user);
    }

    public List<User> findAll() {
        return this.usersDao.findAll();
    }

    public void deleteUser(long id) {
        verifyUserExistById(id);
        this.usersDao.delete(id);
    }

    public void saveUser(User user) {
        this.usersDao.save(user);
    }

    public String findPasswordByLogin(String login) {
        verifyUserExistByLogin(login);
        return this.usersDao.findPasswordByLogin(login);
    }

    public void saveToken(String login, String token) {
        verifyUserExistByLogin(login);
        this.usersDao.saveToken(login, token);
    }

    public void deleteToken(String token) {
        verifyUserExistByToken(token);
        this.usersDao.deleteToken(token);
    }

    public User findUserByToken(String token) {
        return this.usersDao.findUserByToken(token);
    }
}
