package ru.itis.factories;

import ru.itis.dao.CarsDao;
import ru.itis.dao.UsersDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;

public class DaoFactory {

    private static DaoFactory instance;

    private Properties properties;
    private UsersDao usersDao;
    private CarsDao carsDao;


    private DaoFactory() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("/home/dmitry/Desktop/JavaItis" +
                    "/SimpleSite/src/main/resources/dao.properties"));
            String usersDaoClass = properties.getProperty("usersdao.class");
            String carsDaoClass = properties.getProperty("carsdao.class");


            Class clazzUser = Class.forName(usersDaoClass);
            Class clazzCar = Class.forName(carsDaoClass);

            Constructor constructorUser = clazzUser.getConstructor(Connection.class);
            Constructor constructorCar = clazzCar.getConstructor(Connection.class);

            this.usersDao = (UsersDao) constructorUser.newInstance(ConnectionFactory.getInstance().getConnection());
            this.carsDao = (CarsDao) constructorCar.newInstance(ConnectionFactory.getInstance().getConnection());

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static {
        instance = new DaoFactory();
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public CarsDao getCarsDao() {
        return carsDao;
    }
}
