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
    private static final String SQL_UPDATE_USERS = "UPDATE car_users SET u_name = ?, age = ?, u_password = ?, "
            + ", city = ? WHERE id = ?;";
    //language=SQL
    private static final String SQL_ADD_USERS = "INSERT INTO car_users (u_name, age, u_password, city)"
            + " VALUES (?, ?, ?, ?);";

    public UsersDaoJdbcImpl(Connection connection) {
        this.connection =connection;
    }

    public List<User> getAll() {
        try {
            List<User> users = new ArrayList<User>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_ALL_USERS);
            while (resultSet.next()) {
                User u = new User(resultSet.getInt("id"), resultSet.getString("u_name"), resultSet.getInt("age"),
                        resultSet.getString("u_password"), resultSet.getString("city"));
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
            User u = new User(resultSet.getInt("id"), resultSet.getString("u_name"), resultSet.getInt("age"),
                    resultSet.getString("u_password"), resultSet.getString("city"));
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
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public void add(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USERS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
