package ru.itis.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.dao.interfaces.UsersDao;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.UserService;

import java.util.List;

import static ru.itis.chat.validators.UserValidator.verifyUserExistById;
import static ru.itis.chat.validators.UserValidator.verifyUserExistByLogin;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDao usersDao;

    public User findUserById(Long id) {
        return usersDao.findUserById(id);
    }

    public Long findIdByToken(String token) {
        return usersDao.findIdByToken(token);
    }

    public void updateUser(User user) {
        verifyUserExistById(user.getId());
        usersDao.update(user);
    }

    public List<User> findAll() {
        return usersDao.findAll();
    }

    public void deleteUser(Long id) {
        verifyUserExistById(id);
        usersDao.delete(id);
    }

    public void saveUser(User user) {
        this.usersDao.save(user);
    }

    public String findPasswordByLogin(String login) {
        verifyUserExistByLogin(login);
        return usersDao.findPasswordByLogin(login);
    }

    public User findUserByToken(String token) {
        return usersDao.findUserByToken(token);
    }

    @Override
    public User findUserByLogin(String login) {
        verifyUserExistByLogin(login);
        return usersDao.findUserByLogin(login);
    }
}
