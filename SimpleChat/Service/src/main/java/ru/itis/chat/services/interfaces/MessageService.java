package ru.itis.chat.services.interfaces;

import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.models.Message;

import java.util.List;

public interface MessageService {

    List<MessageDto> findAll(Long chatId);
    List<MessageDto> findAllLastMessages(Long userId, Long chatId);
    void save(Message message, Long chatId);
    void delete(Message message, Long chatId);
    List<MessageDto> getNewMessages();
}
