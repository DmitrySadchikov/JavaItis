package ru.itis.factories;

import ru.itis.dao.UsersDao;
import ru.itis.models.User;
import ru.itis.services.UserService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class ServiceFactory {

    private static ServiceFactory instance;
    private Properties properties;
    private UserService userService;

    private ServiceFactory() {
        try {
            this.properties = new Properties();
            properties.load(new FileInputStream("/home/dmitry/Desktop/JavaItis/JdbsDemo" +
                    "/src/main/resources/service.properties"));
            String serviceClass = properties.getProperty("service.class");

            Class clazz = Class.forName(serviceClass);

            Constructor constructor = clazz.getConstructor(UsersDao.class);
            this.userService = (UserService) constructor.newInstance(DaoFactory.getInstance().getUsersDao());

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
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
        instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return this.userService;
    }
}
