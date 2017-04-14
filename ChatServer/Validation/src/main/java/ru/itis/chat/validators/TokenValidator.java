package ru.itis.chat.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenValidator {

    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TokenValidator(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String VERIFY_TOKEN_EXIST = "SELECT CASE WHEN EXISTS " +
            "(SELECT t.* FROM token t WHERE t.token = :token AND t.user_fk IN (SELECT u.id FROM chat_user u " +
            " WHERE u.login <> :login_)) THEN TRUE ELSE FALSE END";

    public static void verifyTokenExistByLogin(String token, String login) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("token", token);
        namedParameters.put("login_", login);
        Boolean isExist = namedParameterJdbcTemplate.queryForObject(VERIFY_TOKEN_EXIST,
                namedParameters, Boolean.class);
        if(isExist)
            throw new IllegalArgumentException();

    }
}
