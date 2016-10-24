package ru.itis.dao;

import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoJdbcImpl implements UsersDao {

    private Connection connection;

    //language=SQL
    private static final String SQL_ALL_USERS = "SELECT * FROM car_users";
    //language=SQL
    private static final String SQL_FIND_USERS = "SELECT * FROM car_users WHERE id = ?";
    //language=SQL
    private static final String SQL_DELETE_USERS = "DELETE FROM car_users WHERE id = ?";
    //language=SQL
    private static final String SQL_UPDATE_USERS = "UPDATE car_users SET password_ = ?," +
            "last_name = ?, first_name = ?, age = ?, city = ? WHERE id = ?;";
    //language=SQL
    private static final String SQL_ADD_USERS = "INSERT INTO car_users (login, password_, " +
            "last_name, first_name, age, city, token)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?);";
    //language=SQL
    private static final String SQL_GET_PASS = "SELECT password_ FROM car_users WHERE login = ?";
    //language=SQL
    private static final String SQL_SET_TOKEN = "UPDATE car_users SET token = ? WHERE login = ?";
    //language=SQL
    private static final String SQL_FIND_TOKEN = "SELECT id FROM car_users WHERE token = ?";

    public UsersDaoJdbcImpl(Connection connection) {
        this.connection =connection;
    }

    public List<User> getAll() {
        try {
            List<User> users = new ArrayList<User>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_ALL_USERS);
            while (resultSet.next()) {
                User u = new User.Builder()
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password_"))
                        .lastName(resultSet.getString("last_name"))
                        .firstName(resultSet.getString("first_name"))
                        .age(resultSet.getInt("age"))
                        .city(resultSet.getString("city"))
                        .token(resultSet.getString("token"))
                        .build();
                users.add(u);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public User find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USERS);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User u = new User.Builder()
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password_"))
                    .lastName(resultSet.getString("last_name"))
                    .firstName(resultSet.getString("first_name"))
                    .age(resultSet.getInt("age"))
                    .city(resultSet.getString("city"))
                    .token(resultSet.getString("token"))
                    .build();
            return u;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USERS);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(User user) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USERS);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setInt(6, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public void add(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USERS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getCity());
            preparedStatement.setString(7, user.getToken());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String getPassword(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_PASS);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String result = resultSet.getString("password_");
            return result;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void setToken(String login, String token) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SET_TOKEN);
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, login);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int find(String token) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_TOKEN);
            preparedStatement.setString(1, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
