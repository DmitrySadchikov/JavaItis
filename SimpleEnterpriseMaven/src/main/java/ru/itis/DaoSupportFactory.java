package ru.itis;

import ru.itis.dao.UsersDao;
import ru.itis.service.SimpleUsersService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DaoSupportFactory {
    private static DaoSupportFactory instance;

    private Properties properties;
    private UsersDao usersDao;
    private SimpleUsersService service;

    private DaoSupportFactory() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("/home/dmitry/Desktop/JavaItis" +
                    "/SimpleEnterpriseMaven/src/resources/dao.properties"));

            String usersDaoClass = properties.getProperty("usersdao.class");
            String serviceClass = properties.getProperty("service.class");

            this.usersDao = (UsersDao)Class.forName(usersDaoClass).newInstance();
            this.service = (SimpleUsersService) Class.forName(serviceClass).newInstance();

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InstantiationException e) {
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

    public SimpleUsersService getService() {
        return service;
    }
}
