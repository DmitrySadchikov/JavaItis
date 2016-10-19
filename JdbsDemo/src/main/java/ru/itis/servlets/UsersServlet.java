package ru.itis.servlets;

import ru.itis.factories.ServiceFactory;
import ru.itis.models.User;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UsersServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> users = userService.getAll();
        try {
            PrintWriter out = response.getWriter();
            out.println("<h1>Список пользователей:</h1>");
            out.println("<p>");
            for(User user : users) {
                out.println(user);
            }
            out.println("</p>");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


    }
}
