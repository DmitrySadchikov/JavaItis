package ru.itis.chat.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChatValidator {

    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ChatValidator(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String VERIFY_CHAT_EXIST_BY_ID = "SELECT CASE WHEN EXISTS " +
            "(SELECT * FROM chat WHERE id = :id) THEN TRUE ELSE FALSE END";

    //language=SQL
    private static final String VERIFY_CHAT_EXIST_BY_NAME = "SELECT CASE WHEN EXISTS " +
            "(SELECT * FROM chat WHERE name = :name_) THEN TRUE ELSE FALSE END";

    public static void verifyChatExistById(Long chatId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("id", chatId);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(VERIFY_CHAT_EXIST_BY_ID,
                namedParameters, Boolean.class);
        if(!isExist)
            throw new IllegalArgumentException();
    }

    public static void verifyChatExistByName(String name) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("name_", name);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(VERIFY_CHAT_EXIST_BY_NAME,
                namedParameters, Boolean.class);
        if(!isExist)
            throw new IllegalArgumentException();
    }
}
