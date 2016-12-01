package ru.itis.chat.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.chat.dao.interfaces.UsersDao;
import ru.itis.chat.models.User;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UsersDaoImpl implements UsersDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM chat_user";
    //language=SQL
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM chat_user WHERE id = :id";
    //language=SQL
    private static final String SQL_FIND_USERS_IN_CHAT = "SELECT * FROM chat_user " +
            "WHERE id IN (SELECT user_fk FROM user_chat WHERE chat_fk = :chat_fk)";
    //language=SQL
    private static final String SQL_DELETE_USER = "DELETE FROM chat_user WHERE id = :id";
    //language=SQL
    private static final String SQL_UPDATE_USER = "UPDATE chat_user SET hash_password = :hash_password," +
            "last_name = :last_name, first_name = :first_name WHERE id = :id;";
    //language=SQL
    private static final String SQL_SAVE_USER = "INSERT INTO chat_user (login, hash_password, last_name, first_name)"
            + " VALUES (:login_, :hash_password, :last_name, :first_name);";
    //language=SQL
    private static final String SQL_FIND_PASS_BY_LOGIN = "SELECT hash_password FROM chat_user WHERE login = :login_";
    //language=SQL
    public static final String SQL_FIND_ID_BY_TOKEN = "SELECT user_fk FROM token WHERE token = :token";
    //language=SQL
    private static final String SQL_FIND_USER_BY_TOKEN = "SELECT u.*, t.* FROM chat_user u " +
            "INNER JOIN token t ON u.id = t.user_fk WHERE t.token = :token";
    //language=SQL
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM chat_user WHERE login = :login_";

    @Autowired
    public UsersDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<User> findAll() {
        return namedParameterJdbcTemplate.query(SQL_FIND_ALL_USERS,
                (resultSet, i) -> new User.Builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("hash_password"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .build());
    }

    @Override
    public List<User> findAllUsersInChat(Long chatId) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("chat_fk", chatId);
        return namedParameterJdbcTemplate.query(SQL_FIND_USERS_IN_CHAT, namedParameters,
                ((resultSet, i) -> new User.Builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("hash_password"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .build()));
    }

    public User findUserById(Long id) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_USER_BY_ID, namedParameters,
                (resultSet, i) -> new User.Builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("hash_password"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .build());
    }

    public void delete(Long id) {
        Map<String, Long> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        namedParameterJdbcTemplate.update(SQL_DELETE_USER, namedParameters);
    }

    public void update(User user) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("hash_password", user.getPassword());
        namedParameters.put("last_name", user.getLastName());
        namedParameters.put("first_name", user.getFirstName());
        namedParameters.put("id", user.getId());
        namedParameterJdbcTemplate.update(SQL_UPDATE_USER, namedParameters);
    }

    public void save(User user) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("login_", user.getLogin());
        namedParameters.put("hash_password", user.getPassword());
        namedParameters.put("last_name", user.getLastName());
        namedParameters.put("first_name", user.getFirstName());
        namedParameterJdbcTemplate.update(SQL_SAVE_USER, namedParameters);
    }

    public String findPasswordByLogin(String login) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("login_", login);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_PASS_BY_LOGIN, namedParameters,
                (resultSet, i) -> resultSet.getString("hash_password"));
    }

    public Long findIdByToken(String token) {
        Map<String, String> nameParameters = new HashMap<>();
        nameParameters.put("token", token);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_ID_BY_TOKEN, nameParameters,
                (resultSet, i) -> resultSet.getLong("user_fk"));
    }

    public User findUserByToken(String token) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("token", token);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_USER_BY_TOKEN, namedParameters,
                (resultSet, i) -> new User.Builder()
                        .id(resultSet.getLong("user_fk"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("hash_password"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .build());
    }

    @Override
    public User findUserByLogin(String login) {
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("login_", login);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_USER_BY_LOGIN, namedParameters,
                (resultSet, i) -> new User.Builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("hash_password"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .build());
    }
}
