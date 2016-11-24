package ru.itis.chat.services.interfaces;

import ru.itis.chat.models.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll(long chatId);
}
