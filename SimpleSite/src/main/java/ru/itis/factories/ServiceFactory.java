package ru.itis.factories;

import ru.itis.dao.CarsDao;
import ru.itis.dao.UsersDao;
import ru.itis.services.CarService;
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
    private CarService carService;

    private ServiceFactory() {
        try {
            this.properties = new Properties();
            properties.load(new FileInputStream("/home/dmitry/Desktop/JavaItis" +
                    "/SimpleSite/src/main/resources/service.properties"));
            String userServiceClass = properties.getProperty("userService.class");
            String carServiceClass = properties.getProperty("carService.class");

            Class userClazz = Class.forName(userServiceClass);
            Class carClazz = Class.forName(carServiceClass);

            Constructor userClazzConstructor = userClazz.getConstructor(UsersDao.class);
            this.userService = (UserService) userClazzConstructor.newInstance(DaoFactory.getInstance().getUsersDao());

            Constructor carClazzConstructor = carClazz.getConstructor(CarsDao.class);
            this.carService = (CarService) carClazzConstructor.newInstance(DaoFactory.getInstance().getCarsDao());
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

    public CarService getCarService() {
        return carService;
    }
}
