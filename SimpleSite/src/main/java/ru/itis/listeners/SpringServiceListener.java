package ru.itis.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringServiceListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:service.xml");
        servletContextEvent.getServletContext().setAttribute("service", applicationContext);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
