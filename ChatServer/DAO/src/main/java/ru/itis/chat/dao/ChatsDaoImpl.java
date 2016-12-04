package ru.itis.chat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.chat.dao.interfaces.ChatsDao;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ChatsDaoImpl implements ChatsDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_ALL_CHATS = "SELECT c.*, u.first_name, u.last_name," +
            "u.login, u.hash_password FROM chat c INNER JOIN" +
            " chat_user u ON c.creator_fk = u.id WHERE c.id IN (SELECT chat_fk FROM user_chat WHERE user_fk = :user_fk)";
    //language=SQL
    private static final String SQL_SAVE_CHAT = "INSERT INTO chat (name, creator_fk) " +
            "VALUES (:name_, :creator)";
    //language=SQL
    private static final String SQL_FIND_ID_BY_NAME = "SELECT * FROM chat WHERE name = :name_";

    @Autowired
    public ChatsDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Chat> findAll(Long userId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("user_fk", userId);
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_CHATS, namedParameters,
                (resultSet, i) -> new Chat.Builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .creator(new User.Builder()
                                .id(resultSet.getLong("creator_fk"))
                                .lastName(resultSet.getString("last_name"))
                                .firstName(resultSet.getString("first_name"))
                                .login(resultSet.getString("login"))
                                .password(resultSet.getString("hash_password"))
                                .build())
                        .build());
    }

    @Override
    public void save(Chat chat) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("name_", chat.getName());
        namedParameters.put("creator", chat.getCreator().getId());
        namedParameterJdbcTemplate.update(SQL_SAVE_CHAT, namedParameters);
    }

    @Override
    public Long findIdByName(String name) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("name_", name);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_ID_BY_NAME, namedParameters,
                (resultSet, i) -> resultSet.getLong("id"));
    }

}
