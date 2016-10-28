package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.models.User;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext sc = config.getServletContext();
        ApplicationContext context = (ApplicationContext) sc.getAttribute("service");
        userService = (UserService) context.getBean("userService");
        sc.log("Recourse to userService from UsersServlet");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html; charset=UTF-8");

            List<User> users = userService.getAll();
            req.setAttribute("CarUsers", users);

            getServletContext().getRequestDispatcher("/jsp/list.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


}
