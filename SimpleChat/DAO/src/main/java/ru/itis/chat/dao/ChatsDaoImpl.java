package ru.itis.chat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.chat.models.Chat;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChatsDaoImpl implements ChatsDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_ALL_CHATS = "SELECT * FROM chat WHERE id IN " +
            "(SELECT chat_fk FROM user_chat WHERE user_fk = :user_fk)";
    //language=SQL
    private static final String SQL_SAVE_CHAT = "INSERT INTO chat (name) " +
            "VALUES (:name_)";
    //language=SQL
    private static final String SQL_FIND_ID = "SELECT * FROM chat WHERE name = :name_";

    @Autowired
    public ChatsDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Chat> findAll(long userId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("user_fk", userId);
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_CHATS, namedParameters,
                (resultSet, i) -> new Chat.Builder().name(resultSet
                        .getString("name")).build());
    }

    @Override
    public void save(Chat chat) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("name_", chat.getName());
        namedParameterJdbcTemplate.update(SQL_SAVE_CHAT, namedParameters);
    }

    @Override
    public Long findIdByName(String name) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("name_", name);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_ID, namedParameters,
                (resultSet, i) -> resultSet.getLong("id"));
    }
}
