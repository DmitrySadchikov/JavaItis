package ru.itis.servlets;

import ru.itis.factories.ServiceFactory;
import ru.itis.models.Car;
import ru.itis.services.CarService;
import ru.itis.services.UserService;

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
    public void init() throws ServletException {
        super.init();
        carService = ServiceFactory.getInstance().getCarService();
        userService = ServiceFactory.getInstance().getUserService();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.setContentType("text/html; charset=UTF-8");
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


            if(make.equals("") || number.equals("")) {
                req.setAttribute("error", "Field * must not be empty");
                doGet(req, resp);
            }
            else {
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

                            getServletContext().getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
                            //resp.sendRedirect("/profile");
                        }
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
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
