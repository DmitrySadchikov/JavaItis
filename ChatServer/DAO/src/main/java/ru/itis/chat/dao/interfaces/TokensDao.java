package ru.itis.chat.dao.interfaces;

import ru.itis.chat.models.Token;

public interface TokensDao {

    void save(Token token);
    void delete(Token token);

}
