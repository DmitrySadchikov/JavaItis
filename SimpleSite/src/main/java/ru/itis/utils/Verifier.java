package ru.itis.utils;

import ru.itis.factories.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Verifier {

    private static Connection connection;

    static {
        connection = ConnectionFactory.getInstance().getConnection();
    }
    // language=SQL
    private static final String SQL_FIND_USER = "SELECT * FROM car_users WHERE id = ?;";
    //language=SQL
    private static final String SQL_FIND_LOGIN = "SELECT * FROM car_users WHERE login = ?";
    // language=SQL
    private static final String SQL_FIND_CAR = "SELECT * FROM cars WHERE id = ?;";
    //language=SQL
    private static final String SQL_FIND_TOKEN = "SELECT * FROM car_users WHERE token = ?";
    //language=SQL
    private static final String SQL_FIND_NUMBER = "SELECT * FROM cars WHERE number_ = ?";


    public static void verifyUserExist(int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER);
            preparedStatement.setInt(1, userId);

            ResultSet result = preparedStatement.executeQuery();

            if (!result.next()) {
                throw new IllegalArgumentException("USER_NOT_FOUND");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void verifyUserExist(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet result = preparedStatement.executeQuery();

            if (!result.next()) {
                throw new IllegalArgumentException("USER_NOT_FOUND");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void verifyUserExistByToken(String token) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_TOKEN);
            preparedStatement.setString(1, token);

            ResultSet result = preparedStatement.executeQuery();

            if (!result.next()) {
                throw new IllegalArgumentException("USER_NOT_FOUND");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void verifyCarExist(int carId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CAR);
            preparedStatement.setInt(1, carId);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.next()) {
                throw new IllegalArgumentException("CAR_NOT_FOUND");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void verifyCarExist(String number) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_NUMBER);
            preparedStatement.setString(1, number);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.next()) {
                throw new IllegalArgumentException("CAR_NOT_FOUND");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
