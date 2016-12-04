package ru.itis.chat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.chat.dao.interfaces.TokensDao;
import ru.itis.chat.models.Token;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TokensDaoImpl implements TokensDao {


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TokensDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQl
    private static final String SQL_SAVE_TOKEN = "INSERT INTO token (token, user_fk) VALUES (:token, :user_fk)";
    //language=SQL
    private static final String SQL_DELETE_TOKEN = "DELETE FROM token WHERE user_fk = :user_fk";

    @Override
    public void save(Token token) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("token", token.getToken());
        namedParameters.put("user_fk", token.getUser().getId());
        namedParameterJdbcTemplate.update(SQL_SAVE_TOKEN, namedParameters);
    }

    @Override
    public void delete(Token token) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("user_fk", token.getUser().getId());
        namedParameterJdbcTemplate.update(SQL_DELETE_TOKEN, namedParameters);
    }
}
