package ru.itis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import ru.itis.models.Car;
import ru.itis.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UsersDaoImpl implements UsersDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_ALL_USERS = "SELECT * FROM car_users";
    //language=SQL
    private static final String SQL_CARS_OF_USER = "SELECT * FROM cars WHERE id_user = :id_user";
    //language=SQL
    private static final String SQL_FIND_USERS = "SELECT * FROM car_users WHERE id = :id";
    //language=SQL
    private static final String SQL_DELETE_USERS = "DELETE FROM car_users WHERE id = :id";
    //language=SQL
    private static final String SQL_UPDATE_USERS = "UPDATE car_users SET password_ = :password_," +
            "last_name = :last_name, first_name = :first_name, age = :age, city = :city WHERE id = :id;";
    //language=SQL
    private static final String SQL_ADD_USERS = "INSERT INTO car_users (login, password_, " +
            "last_name, first_name, age, city, token)"
            + " VALUES (:login_, :password_, :last_name, :first_name, :age, :city, :token);";
    //language=SQL
    private static final String SQL_GET_PASS = "SELECT password_ FROM car_users WHERE login = :login_";
    //language=SQL
    private static final String SQL_SET_TOKEN = "UPDATE car_users SET token = :token WHERE login = :login_";
    //language=SQL
    private static final String SQL_FIND_TOKEN = "SELECT * FROM car_users WHERE token = :token";
    //language=SQL
    private static final String SQL_DELETE_TOKEN = "UPDATE car_users SET token = NULL WHERE token = :token";

    @Autowired
    public UsersDaoImpl(DriverManagerDataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Car> carsOfUser(int id) {
        Map<String, Integer> namedParameters = new HashMap<String, Integer>();
        namedParameters.put("id_user", id);
        return namedParameterJdbcTemplate.query(SQL_CARS_OF_USER, namedParameters, new RowMapper<Car>() {
            public Car mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Car.Builder()
                        .make(resultSet.getString("make"))
                        .number(resultSet.getString("number_"))
                        .color(resultSet.getString("color"))
                        .build();
            }
        });
    }

    public List<User> getAll() {
        return namedParameterJdbcTemplate.query(SQL_ALL_USERS, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User.Builder()
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password_"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .age(resultSet.getInt("age"))
                        .city(resultSet.getString("city"))
                        .token(resultSet.getString("token"))
                        .cars(carsOfUser(resultSet.getInt("id")))
                        .build();
            }
        });
    }


    public User findId(int id) {
        Map<String, Integer> namedParameters = new HashMap<String, Integer>();
        namedParameters.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_USERS, namedParameters, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User.Builder()
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password_"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .age(resultSet.getInt("age"))
                        .city(resultSet.getString("city"))
                        .token(resultSet.getString("token"))
                        .cars(carsOfUser(resultSet.getInt("id")))
                        .build();
            }
        });
    }

    public void delete(int id) {
        Map<String, Integer> namedParameters = new HashMap<String, Integer>();
        namedParameters.put("id", id);
        namedParameterJdbcTemplate.update(SQL_DELETE_USERS, namedParameters);
    }

    public void update(User user) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("password_", user.getPassword());
        namedParameters.put("last_name", user.getLastName());
        namedParameters.put("first_name", user.getFirstName());
        namedParameters.put("age", user.getAge());
        namedParameters.put("city", user.getCity());
        namedParameters.put("id", user.getId());
        namedParameterJdbcTemplate.update(SQL_UPDATE_USERS, namedParameters);
    }

    public void add(User user) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("login_", user.getLogin());
        namedParameters.put("password_", user.getPassword());
        namedParameters.put("last_name", user.getLastName());
        namedParameters.put("first_name", user.getFirstName());
        namedParameters.put("age", user.getAge());
        namedParameters.put("city", user.getCity());
        namedParameters.put("token", user.getToken());
        namedParameterJdbcTemplate.update(SQL_ADD_USERS, namedParameters);
    }

    public String getPassword(String login) {
        Map<String, String> namedParameters = new HashMap<String, String>();
        namedParameters.put("login_", login);
        return namedParameterJdbcTemplate.queryForObject(SQL_GET_PASS, namedParameters, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("password_");
            }
        });
    }

    public void setToken(String login, String token) {
        Map<String, String> nameParameters = new HashMap<String, String>();
        nameParameters.put("token", token);
        nameParameters.put("login_", login);
        namedParameterJdbcTemplate.update(SQL_SET_TOKEN, nameParameters);
    }

    public int findId(String token) {
        Map<String, String> nameParameters = new HashMap<String, String>();
        nameParameters.put("token", token);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_TOKEN, nameParameters, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("id");
            }
        });
    }

    public User findUser(String token) {
        Map<String, String> nameParameters = new HashMap<String, String>();
        nameParameters.put("token", token);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_TOKEN, nameParameters, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                return new User.Builder()
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password_"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .age(resultSet.getInt("age"))
                        .city(resultSet.getString("city"))
                        .token(resultSet.getString("token"))
                        .cars(carsOfUser(resultSet.getInt("id")))
                        .build();
            }
        });
    }

    public void deleteToken(String token) {
        Map<String, String> nameParameters = new HashMap<String, String>();
        nameParameters.put("token", token);
        namedParameterJdbcTemplate.update(SQL_DELETE_TOKEN, nameParameters);
    }
}
