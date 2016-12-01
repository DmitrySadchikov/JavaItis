package ru.itis.chat.dao.interfaces;

import ru.itis.chat.models.User;

import java.util.List;

public interface UsersDao {

    User findUserById(Long id);
    Long findIdByToken(String token);
    List<User> findAll();
    List<User> findAllUsersInChat(Long chatId);
    void delete(Long id);
    void update(User user);
    void save(User user);
    String findPasswordByLogin(String login);
    User findUserByToken(String token);
    User findUserByLogin(String login);

}
