package ru.itis.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.converters.MessageToMessageDtoConverter;
import ru.itis.chat.dao.interfaces.MessagesDao;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.models.Message;
import ru.itis.chat.services.interfaces.MessageService;

import java.util.ArrayList;
import java.util.List;

import static ru.itis.chat.validators.ChatValidator.verifyChatExistById;
import static ru.itis.chat.validators.MessageValidator.verifyMessageExistById;
import static ru.itis.chat.validators.UserValidator.verifyUserExistById;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessagesDao messagesDao;
    @Autowired
    private MessageToMessageDtoConverter messageConverter;

    private List<MessageDto> newMessages;

    public MessageServiceImpl() {
        newMessages = new ArrayList<>();
    }

    @Override
    public List<MessageDto> findAll(Long chatId) {
        verifyChatExistById(chatId);
        List<Message> messages = messagesDao.findAll(chatId);
        List<MessageDto> result = new ArrayList<>();
        for(Message message : messages) {
            result.add(messageConverter.convert(message));
        }
        return result;
    }

    @Override
    public List<MessageDto> findAllLastMessages(Long userId, Long chatId) {
        verifyUserExistById(userId);
        verifyChatExistById(chatId);
        List<Message> messages = messagesDao.findAllLastMessages(userId, chatId);
        for(Message message : messages) {
            newMessages.add(messageConverter.convert(message));
        }
        return newMessages;
    }

    @Override
    public void save(Message message, Long chatId) {
        messagesDao.save(message, chatId);
    }

    @Override
    public void delete(Message message, Long chatId) {
        verifyChatExistById(chatId);
        verifyMessageExistById(message.getId());
        messagesDao.delete(message, chatId);
    }

    public List<MessageDto> getNewMessages() {
        return newMessages;
    }
}
