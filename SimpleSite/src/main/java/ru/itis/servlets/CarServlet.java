package ru.itis.servlets;

import ru.itis.models.Car;
import ru.itis.services.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CarServlet extends HttpServlet {

    private CarService carService;

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
            Integer power = Integer.parseInt(req.getParameter("power"));
            String color = req.getParameter("color");

            Cookie cookie = req.getCookies()[0];
            String token = cookie.getValue();

            carService.addCar(new Car.Builder()
                    .make(make)
                    .power(power)
                    .color(color)
                    .build());

            resp.sendRedirect("/profile");

        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
