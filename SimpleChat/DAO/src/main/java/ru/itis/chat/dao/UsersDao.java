package ru.itis.chat.dao;

import ru.itis.chat.models.User;

import java.util.List;

public interface UsersDao {

    User findUserById(long id);
    long findIdByToken(String token);
    List<User> findAll();
    List<User> findAllUsersInChat(long chatId);
    void delete(long id);
    void update(User user);
    void save(User user);
    String findPasswordByLogin(String login);
    void saveToken(String login, String token);
    void deleteToken(String token);
    User findUserByToken(String token);

}
