package ru.itis.chat.dao.interfaces;

import ru.itis.chat.models.Chat;

import java.util.List;

public interface ChatsDao {

    Chat find(Long chatId);
    List<Chat> findAll(Long userId);
    void save(Chat chat);
    Long findIdByName(String name);
}
