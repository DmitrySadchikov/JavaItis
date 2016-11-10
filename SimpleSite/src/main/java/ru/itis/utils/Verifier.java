package ru.itis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Verifier {

    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public Verifier(DriverManagerDataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    // language=SQL
    private static final String SQL_FIND_USER = "SELECT * FROM car_users WHERE id = :id;";
    //language=SQL
    private static final String SQL_FIND_LOGIN = "SELECT * FROM car_users WHERE login = :login_";
    // language=SQL
    private static final String SQL_FIND_CAR = "SELECT * FROM cars WHERE id = :id;";
    //language=SQL
    private static final String SQL_FIND_TOKEN = "SELECT * FROM car_users WHERE token = :token";
    //language=SQL
    private static final String SQL_FIND_NUMBER = "SELECT * FROM cars WHERE number_ = :number_";


    public static void verifyUserExist(int userId) {
        Map<String, Integer> namedParameters = new HashMap<String, Integer>();
        namedParameters.put("id", userId);
        if(namedParameterJdbcTemplate.queryForList(SQL_FIND_USER, namedParameters).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static void verifyUserExist(String login) {
        Map<String, String> namedParameters = new HashMap<String, String>();
        namedParameters.put("login_", login);
        if(namedParameterJdbcTemplate.queryForList(SQL_FIND_LOGIN, namedParameters).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static void verifyUserExistByToken(String token) {
        Map<String, String> namedParameters = new HashMap<String, String>();
        namedParameters.put("token", token);
        if(namedParameterJdbcTemplate.queryForList(SQL_FIND_TOKEN, namedParameters).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static void verifyCarExist(int carId) {
        Map<String, Integer> namedParameters = new HashMap<String, Integer>();
        namedParameters.put("id", carId);
        if(namedParameterJdbcTemplate.queryForList(SQL_FIND_CAR, namedParameters).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public static void verifyCarExist(String number) {
        Map<String, String> namedParameters = new HashMap<String, String>();
        namedParameters.put("number_", number);
        if(!namedParameterJdbcTemplate.queryForList(SQL_FIND_NUMBER, namedParameters).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

}
