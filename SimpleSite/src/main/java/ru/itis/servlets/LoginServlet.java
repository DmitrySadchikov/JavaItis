package ru.itis.servlets;

import ru.itis.factories.ServiceFactory;
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

import static ru.itis.hash.Whirlpool.toHash;

public class LoginServlet extends HttpServlet {

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
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
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
            String hash = toHash(password);
            if(!hash.equals(userService.getPassword(login))) {
                req.setAttribute("error", "Incorrect password");
                doGet(req, resp);
            }
            else {
                String token = new BigInteger(130, new SecureRandom()).toString(32);
                Cookie cookie = new Cookie("token", token);
                resp.addCookie(cookie);
                userService.setToken(login, token);
                resp.sendRedirect("/profile");
            }


        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", "User not found");
            doGet(req, resp);
        }
    }
}
