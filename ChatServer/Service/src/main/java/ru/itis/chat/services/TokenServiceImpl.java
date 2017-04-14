package ru.itis.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.dao.interfaces.TokensDao;
import ru.itis.chat.models.Token;
import ru.itis.chat.services.interfaces.TokenService;

import static ru.itis.chat.validators.TokenValidator.verifyTokenExistByLogin;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokensDao tokensDao;

    @Override
    public void saveToken(Token token) {
        verifyTokenExistByLogin(token.getToken(), token.getUser().getLogin());
        tokensDao.save(token);
    }
}
