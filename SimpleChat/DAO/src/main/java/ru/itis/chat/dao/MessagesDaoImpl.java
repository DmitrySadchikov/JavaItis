package ru.itis.chat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.chat.models.Message;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MessagesDaoImpl implements MessagesDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_ALL_MESSAGES = "SELECT * FROM message " +
            "WHERE chat_fk = :chat_fk";


    @Autowired
    public MessagesDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Message> findAll(long chatId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("chat_fk", chatId);
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_MESSAGES, namedParameters,
                (resultSet, i) -> new Message.Builder()
                        .id(resultSet.getLong("id"))
                        .text(resultSet.getString("text_message"))
                        .time(resultSet.getTime("time_message"))
                        .date(resultSet.getDate("date_message"))
                        .userId(resultSet.getLong("user_fk"))
                        .build());
    }

}
