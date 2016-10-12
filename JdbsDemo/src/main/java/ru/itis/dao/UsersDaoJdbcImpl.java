package ru.itis.dao;

import ru.itis.DaoSupportFactory;
import ru.itis.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoJdbcImpl implements UsersDao {

    private Connection connection;

    public UsersDaoJdbcImpl() {
        connection = DaoSupportFactory.getInstance().getConnection();
    }

    public List<User> getAll() {
        try {
            List<User> users = new ArrayList<User>();
            
            ResultSet resultSet = statement.executeQuery("SELECT * FROM car_users");
            while (resultSet.next()) {
                User u = new User(resultSet.getString("u_name"), resultSet.getInt("age"),
                        resultSet.getString("u_password"), resultSet.getString("city"));
                users.add(u);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return null;
    }


    public void find(int id) {

    }

    public void delete(int id) {

    }

    public void update(User car) {

    }

    public void add(User car) {

    }
}
