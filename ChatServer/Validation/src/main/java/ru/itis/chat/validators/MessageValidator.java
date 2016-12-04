package ru.itis.chat.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageValidator {

    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public MessageValidator(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String VERIFY_MESSAGE_EXIST_BY_ID = "SELECT CASE WHEN EXISTS" +
            " (SELECT * FROM message WHERE id = :id) THEN TRUE ELSE FALSE END";

    public static void verifyMessageExistById(Long messageId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("id", messageId);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(VERIFY_MESSAGE_EXIST_BY_ID,
                namedParameters, Boolean.class);
        if(!isExist)
            throw new IllegalArgumentException();
    }
}
