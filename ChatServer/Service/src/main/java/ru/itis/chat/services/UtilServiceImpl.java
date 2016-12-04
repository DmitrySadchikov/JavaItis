package ru.itis.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.dao.interfaces.UtilsDao;
import ru.itis.chat.services.interfaces.UtilService;

import static ru.itis.chat.validators.ChatValidator.verifyChatExistById;
import static ru.itis.chat.validators.UserValidator.verifyUserExistById;

@Service
public class UtilServiceImpl implements UtilService {

    @Autowired
    private UtilsDao utilsDao;

    @Override
    public void saveUserInChat(Long chatId, Long userId) {
        verifyUserExistById(userId);
        verifyChatExistById(chatId);
        utilsDao.saveUserInChat(chatId, userId);
    }
}
