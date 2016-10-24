package ru.itis.factories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory instance;

    private Properties properties;
    private Connection connection;

    private ConnectionFactory() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("/home/dmitry/Desktop/JavaItis" +
                    "/SimpleSite/src/main/resources/connection.properties"));
            this.connection = null;
            String driver = properties.getProperty("jdbc.driver");
            String URL = properties.getProperty("jdbc.URL");
            String name = properties.getProperty("jdbc.name");
            String password = properties.getProperty("jdbc.password");

            Class.forName(driver);
            this.connection = DriverManager.getConnection(URL, name, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static {
        instance = new ConnectionFactory();
    }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
