package ru.itis.chat.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserValidator {

    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserValidator(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String VERIFY_USER_EXIST_BY_ID = "SELECT CASE WHEN EXISTS " +
            "(SELECT * FROM chat_user WHERE id = :id) THEN TRUE ELSE FALSE END";
    //language=SQL
    private static final String VERIFY_USER_EXIST_BY_LOGIN = "SELECT CASE WHEN EXISTS " +
            "(SELECT * FROM chat_user WHERE login = :login_) THEN TRUE ELSE FALSE END";
    //language=SQL
    private static final String VERIFY_USER_EXIST_BY_TOKEN = "SELECT CASE WHEN EXISTS " +
            "(SELECT * FROM chat_user WHERE id IN (SELECT user_fk FROM token WHERE token = :token))" +
            " THEN TRUE ELSE FALSE END";
    //language=SQL
    private static final String VERIFY_USER_EXIST_IN_CHAT = "SELECT CASE WHEN EXISTS " +
            "(SELECT * FROM user_chat WHERE user_fk = :user_fk AND chat_fk = :chat_fk) " +
            "THEN TRUE ELSE FALSE END";


    public static void verifyUserExistById(Long userId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("id", userId);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(VERIFY_USER_EXIST_BY_ID,
                namedParameters, Boolean.class);
        if(!isExist)
            throw new IllegalArgumentException();
    }

    public static void verifyUserExistByLogin(String login) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("login_", login);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(VERIFY_USER_EXIST_BY_LOGIN,
                namedParameters, Boolean.class);
        if(!isExist)
            throw new IllegalArgumentException();
    }

    public static void verifyUserExistByToken(String token) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("token", token);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(VERIFY_USER_EXIST_BY_TOKEN,
                namedParameters, Boolean.class);
        if(!isExist)
            throw new IllegalArgumentException();
    }

    public static void verifyUserExistInChat(Long userId, Long chatId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("user_fk", userId);
        namedParameters.put("chat_fk", chatId);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(VERIFY_USER_EXIST_IN_CHAT,
                namedParameters, Boolean.class);
        if(!isExist)
            throw new IllegalArgumentException();
    }

}
