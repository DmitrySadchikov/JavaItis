package ru.itis.chat.services.interfaces;

import ru.itis.chat.models.Token;

public interface TokenService {

    void saveToken(Token token);
    void deleteToken(Token token);
}
