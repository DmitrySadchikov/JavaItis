package ru.itis.chat.dao;

import ru.itis.chat.models.Chat;

import java.util.List;

public interface ChatsDao {

    List<Chat> findAll(long userId);
    void save(Chat chat);
    Long findIdByName(String name);
}
