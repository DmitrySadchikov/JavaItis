package ru.itis.factories;

import ru.itis.services.UserService;

import java.util.Properties;

public class ServiceFactory {

    private static ServiceFactory instance;
    private Properties properties;
    private UserService userService;

    private ServiceFactory() {
    }
}
