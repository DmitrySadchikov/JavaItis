package ru.itis.dao;

import ru.itis.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoJdbcImpl implements UsersDao {

    private Connection connection;

    public UsersDaoJdbcImpl(Connection connection) {
        this.connection =connection;
    }

    public List<User> getAll() {
        try {
            List<User> users = new ArrayList<User>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM car_users");
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
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM car_users WHERE id=" + id);
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
            Statement statement = connection.createStatement();
            statement.executeQuery("DELETE * FROM car_users WHERE id=" + id);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(User user) {

        // описать случай, когда нет такого пользователя

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE car_users SET u_name=" + user.getName()
                    + ", age=" + user.getAge() + ", u_password=" + user.getPassword()
                    + ", city=" + user.getCity() + " WHERE id=" + user.getId());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public void add(User user) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO car_users (u_name, age, u_password, city)" +
                    " VALUES (" + user.getName() + ", " + user.getAge() + ", "
                    + user.getPassword() + ", " + user.getCity() + ");");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
