package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.models.Car;
import ru.itis.models.User;
import ru.itis.services.CarService;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static ru.itis.utils.Verifier.verifyCarExist;

public class AddCarServlet extends HttpServlet {

    private CarService carService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext sc = config.getServletContext();
        ApplicationContext context = (ApplicationContext) sc.getAttribute("service");
        userService = (UserService) context.getBean("userService");
        carService = (CarService) context.getBean("carService");
        sc.log("Recourse to userService from AddCarServlet");
        sc.log("Recourse to carService from AddCarServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html; charset=UTF-8");
            Cookie[] cookies = req.getCookies();
            if(cookies != null) {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        User user = userService.findUserByToken(token);
                        req.setAttribute("user", user);
                    }
                }
            }
            getServletContext().getRequestDispatcher("/jsp/car.jsp").forward(req, resp);
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

            String make = req.getParameter("make");
            String number = req.getParameter("number");
            String color = req.getParameter("color");

            verifyCarExist(number);
            Cookie[] cookies = req.getCookies();
            if(cookies != null) {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        int id_user = userService.findIdByToken(token);

                        carService.addCar(new Car.Builder()
                                .make(make)
                                .number(number)
                                .color(color)
                                .id_user(id_user)
                                .build());
                        resp.sendRedirect("/addcar");
                    }
                }
            }


        } catch (IllegalArgumentException e) {
            req.setAttribute("error", "The car with this number already exists");
            doGet(req, resp);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } /*catch (ServletException e) {
            throw new IllegalArgumentException(e);
        }*/
    }
}
