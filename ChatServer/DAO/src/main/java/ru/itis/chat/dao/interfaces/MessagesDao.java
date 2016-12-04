package ru.itis.chat.dao.interfaces;

import ru.itis.chat.models.Message;

import java.util.List;

public interface MessagesDao {

    List<Message> findAll(Long chatId);
    List<Message> findAllLastMessages(Long userId, Long chatId);
    void save(Message message, Long chatId);
    void delete(Message message, Long chatId);
}
