package ru.itis.chat.services.interfaces;

import ru.itis.chat.models.Chat;

import java.util.List;

public interface ChatService {

    Chat find(Long chatId);
    List<Chat> findAll(String token);
    void save(Chat chat);
    Long findIdByName(String name);
}
