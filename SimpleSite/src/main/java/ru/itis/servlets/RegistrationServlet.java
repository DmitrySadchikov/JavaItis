package ru.itis.servlets;

import ru.itis.factories.ServiceFactory;
import ru.itis.models.User;
import ru.itis.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;

import static java.lang.Integer.parseInt;
import static ru.itis.hash.Whirlpool.toHash;

public class RegistrationServlet extends HttpServlet{

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html; charset=UTF-8");
            getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        try {
            req.setCharacterEncoding("UTF-8");

            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String lastName = req.getParameter("last_name");
            String firstName = req.getParameter("first_name");
            String s = req.getParameter("age");
            Integer age = 0;
            if(!s.equals(""))
                age = parseInt(s);
            String city = req.getParameter("city");
            int i = 0;
            if(login.equals("") || password.equals("") ||
                    lastName.equals("") || firstName.equals("")) {
                req.setAttribute("error", "Field * must not be empty");
                doGet(req, resp);
            }

            String token = new BigInteger(130, new SecureRandom()).toString(32);
            Cookie cookie = new Cookie("token", token);
            resp.addCookie(cookie);

            userService.addUser(new User.Builder()
                    .login(login)
                    .password(toHash(password))
                    .lastName(lastName)
                    .firstName(firstName)
                    .age(age)
                    .city(city)
                    .token(token)
                    .build());

            resp.sendRedirect("/profile");

        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}


