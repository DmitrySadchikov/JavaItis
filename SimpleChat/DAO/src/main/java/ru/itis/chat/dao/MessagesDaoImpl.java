package ru.itis.chat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.chat.dao.interfaces.MessagesDao;
import ru.itis.chat.models.Message;
import ru.itis.chat.models.User;

import javax.sql.DataSource;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MessagesDaoImpl implements MessagesDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_ALL_MESSAGES_FOR_THE_YEAR = "SELECT m.*, u.login, " +
            "u.hash_password, u.last_name, u.first_name FROM message m " +
            "INNER JOIN chat_user u ON m.user_fk = u.id  WHERE chat_fk = :chat_fk " +
            "AND current_date - m.date_message < 365";
    //language=SQL
    private static final String SQL_FIND_ALL_LAST_MESSAGES = "SELECT m.*, u.login," +
            "u.hash_password, u.last_name, u.first_name FROM message m INNER JOIN chat_user u " +
            "ON m.user_fk = u.id WHERE m.id > (SELECT last_message_id FROM last_message " +
            "WHERE user_fk = :user_fk AND chat_fk = :chat_fk LIMIT 1)";
    //language=SQL
    private static final String SQL_SAVE_MESSAGE = "INSERT INTO message (text_message, time_message, " +
            "date_message, user_fk, chat_fk) VALUES (:text_message, :time_message, :date_message, " +
            ":user_fk, :chat_fk)";
    //language=SQL
    private static final String SQL_DELETE_MESSAGE = "DELETE FROM message WHERE user_fk = :user_fk" +
            " AND chat_fk = :chat_fk";


    @Autowired
    public MessagesDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Message> findAll(Long chatId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("chat_fk", chatId);
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_MESSAGES_FOR_THE_YEAR, namedParameters,
                (resultSet, i) -> new Message.Builder()
                        .id(resultSet.getLong("id"))
                        .text(resultSet.getString("text_message"))
                        .date(new Date(resultSet.getDate("time_message").getTime()))
                        .sender(new User.Builder()
                                .id(resultSet.getLong("user_fk"))
                                .lastName(resultSet.getString("last_name"))
                                .firstName(resultSet.getString("first_name"))
                                .login(resultSet.getString("login"))
                                .password(resultSet.getString("hash_password"))
                                .build())
                        .build());
    }

    @Override
    public List<Message> findAllLastMessages(Long userId, Long chatId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("user_fk", userId);
        namedParameters.put("chat_fk", chatId);
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_LAST_MESSAGES, namedParameters,
                (resultSet, i) -> new Message.Builder()
                        .id(resultSet.getLong("id"))
                        .text(resultSet.getString("text_message"))
                        .date(new Date(resultSet.getDate("time_message").getTime()))
                        .sender(new User.Builder()
                                .id(resultSet.getLong("user_fk"))
                                .lastName(resultSet.getString("last_name"))
                                .firstName(resultSet.getString("first_name"))
                                .login(resultSet.getString("login"))
                                .password(resultSet.getString("hash_password"))
                                .build())
                        .build());
    }

    @Override
    public void save(Message message, Long chatId) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("text_message", message.getText());
        namedParameters.put("time_message", new Time(message.getDate().getTime()));
        namedParameters.put("date_message", new java.sql.Date(message.getDate().getTime()));
        namedParameters.put("user_fk", message.getSender().getId());
        namedParameters.put("chat_fk", chatId);
        namedParameterJdbcTemplate.update(SQL_SAVE_MESSAGE, namedParameters);
    }

    @Override
    public void delete(Message message, Long chatId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("user_fk", message.getSender().getId());
        namedParameters.put("chat_fk", chatId);
        namedParameterJdbcTemplate.update(SQL_DELETE_MESSAGE, namedParameters);
    }


}
