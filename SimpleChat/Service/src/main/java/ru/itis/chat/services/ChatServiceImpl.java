package ru.itis.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.dao.ChatsDao;
import ru.itis.chat.dao.MessagesDao;
import ru.itis.chat.dao.UsersDao;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.ChatService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatsDao chatsDao;

    @Autowired
    private MessagesDao messagesDao;

    @Autowired
    private UsersDao usersDao;

    @Override
    public List<Chat> findAll(String token) {
        User user = usersDao.findUserByToken(token);
        List<Chat> chats = chatsDao.findAll(user.getId());
        List<Chat> result = new ArrayList<>();
        for(Chat chat : chats) {
            result.add(new Chat.Builder()
                    .id(chat.getId())
                    .name(chat.getName())
                    .messages(messagesDao.findAll(chat.getId()))
                    .users(usersDao.findAllUsersInChat(chat.getId()))
                    .build());
        }
        return result;
    }

    @Override
    public void save(Chat chat) {
        chatsDao.save(chat);
    }

    @Override
    public Long findIdByName(String name) {
        return chatsDao.findIdByName(name);
    }

}
