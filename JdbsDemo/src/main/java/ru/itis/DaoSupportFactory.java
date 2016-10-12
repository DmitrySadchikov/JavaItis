package ru.itis;

import ru.itis.dao.CarsDao;
import ru.itis.dao.CarsDaoJdbcImpl;
import ru.itis.dao.UsersDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DaoSupportFactory {

    private static DaoSupportFactory instance;

    private Properties properties;
    private UsersDao usersDao;
    private CarsDao carsDao;
    private Connection connection;


    private DaoSupportFactory() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("/home/dmitry/Desktop/JavaItis/JdbsDemo" +
                    "/src/main/resources/dao.properties"));
            String usersDaoClass = properties.getProperty("usersdao.class");
            String carsDaoClass = properties.getProperty("carsdao.class");

            this.usersDao = (UsersDao)Class.forName(usersDaoClass).newInstance();
            this.carsDao = (CarsDaoJdbcImpl)Class.forName(carsDaoClass).newInstance();

            properties.load(new FileInputStream("/home/dmitry/Desktop/JavaItis/JdbsDemo" +
                    "/src/main/resources/connection.properties"));
            this.connection = null;
            String URL = properties.getProperty("URL");
            String name = properties.getProperty("name");
            String password = properties.getProperty("password");

            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, name, password);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static {
        instance = new DaoSupportFactory();
    }

    public static DaoSupportFactory getInstance() {
        return instance;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public CarsDao getCarsDao() {
        return carsDao;
    }

    public Connection getConnection() {
        return connection;
    }
}
