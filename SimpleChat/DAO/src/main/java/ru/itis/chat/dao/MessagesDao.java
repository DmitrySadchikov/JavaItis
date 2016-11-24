package ru.itis.chat.dao;

import ru.itis.chat.models.Message;

import java.util.List;

public interface MessagesDao {

    List<Message> findAll(long chatId);
}
