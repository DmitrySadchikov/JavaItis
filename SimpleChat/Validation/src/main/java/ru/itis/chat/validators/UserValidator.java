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
    private static final String SQL_FIND_USER = "SELECT CASE WHEN EXISTS " +
            "(SELECT * FROM chat_user WHERE id = :id) THEN TRUE ELSE FALSE END";
    //language=SQL
    private static final String SQL_FIND_LOGIN = "SELECT CASE WHEN EXISTS " +
            "(SELECT * FROM chat_user WHERE login = :login_) THEN TRUE ELSE FALSE END";
    //language=SQL
    private static final String SQL_FIND_TOKEN = "SELECT CASE WHEN EXISTS " +
            "(SELECT * FROM chat_user WHERE token = :token) THEN TRUE ELSE FALSE END";


    public static void verifyUserExistById(long userId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("id", userId);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(SQL_FIND_USER,
                namedParameters, Boolean.class);
        if(!isExist) {
            throw new IllegalArgumentException();
        }
    }

    public static void verifyUserExistByLogin(String login) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("login_", login);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(SQL_FIND_LOGIN,
                namedParameters, Boolean.class);
        if(!isExist) {
            throw new IllegalArgumentException();
        }
    }

    public static void verifyUserExistByToken(String token) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("token", token);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(SQL_FIND_TOKEN,
                namedParameters, Boolean.class);
        if(!isExist) {
            throw new IllegalArgumentException();
        }
    }

}
