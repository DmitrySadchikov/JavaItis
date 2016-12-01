package ru.itis.chat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.chat.dao.interfaces.UtilsDao;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UtilsDaoImpl implements UtilsDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_SAVE_USER_IN_CHAT = "INSERT INTO user_chat (chat_fk, user_fk) " +
            "VALUES (:chat_fk, :user_fk)";


    @Autowired
    public UtilsDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void saveUserInChat(Long chatId, Long userId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("chat_fk", chatId);
        namedParameters.put("user_fk", userId);
        namedParameterJdbcTemplate.update(SQL_SAVE_USER_IN_CHAT, namedParameters);
    }

}
