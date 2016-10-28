package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext sc = config.getServletContext();
        ApplicationContext context = (ApplicationContext) sc.getAttribute("service");
        userService = (UserService) context.getBean("userService");
        sc.log("Recourse to userService from LogoutServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    Cookie killMyCookie = new Cookie("token", null);
                    killMyCookie.setMaxAge(0);
                    killMyCookie.setPath("/");
                    resp.addCookie(killMyCookie);
                    userService.deleteToken(token);
                    resp.sendRedirect("/");
                }
            }
        }
    }
}
