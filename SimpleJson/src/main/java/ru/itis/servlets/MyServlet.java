package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import ru.itis.converters.ModelConverter;
import ru.itis.models.Car;
import ru.itis.models.User;
import ru.itis.services.CarService;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyServlet extends HttpServlet {

    private UserService userService;
    private CarService carService;
    private ObjectMapper objectMapper;

    private class RestRequest {
        private Pattern getAllUsers = Pattern.compile("/users");
        private Pattern getCars = Pattern.compile("/users/([0-9]+)/cars");
        //private Pattern setCar = Pattern.compile("/users/([0-9]+)/cars");

        private boolean users;
        private Integer id_user;

        public RestRequest(String pathInfo) throws ServletException {

            Matcher matcher;
            users = false;

            matcher = getCars.matcher(pathInfo);
            if(matcher.find()) {
                id_user = Integer.parseInt(matcher.group(1));
                return;
            }

            matcher = getAllUsers.matcher(pathInfo);
            if (matcher.find()) {
                users = true;
                return;
            }

            throw new ServletException("Invalid URI");
        }

        public Integer getId_user() {
            return id_user;
        }

        public boolean isUsers() {
            return users;
        }

        public void setId_user(Integer id_user) {
            this.id_user = id_user;
        }

        public void setUsers(boolean users) {
            this.users = users;
        }
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext sc = config.getServletContext();
        ApplicationContext context = (ApplicationContext) sc.getAttribute("service");
        objectMapper = new ObjectMapper();
        userService = (UserService) context.getBean("userService");
        carService = (CarService) context.getBean("carService");
        sc.log("Recourse to userService from MyServlet");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            RestRequest resourceValues = new RestRequest(req.getPathInfo());
            ModelConverter converter = new ModelConverter();
            if(resourceValues.isUsers()) {
                if(req.getParameter("age") != null) {
                    User user = userService.findUserByAge(Integer.parseInt(req.getParameter("age")));
                    String stringResponse = objectMapper.writeValueAsString(converter.getUserDto(user));

                    resp.setContentType("application/json");
                    resp.getWriter().write(stringResponse + "\n");
                }
                else  {
                    List<User> list = userService.getAll();
                    for(User user : list) {
                        String stringResponse = objectMapper.writeValueAsString(converter.getUserDto(user));
                        resp.setContentType("application/json");
                        resp.getWriter().write(stringResponse + "\n");
                    }
                }
            }
            else {
                List<Car> cars = userService.carsOfUser(resourceValues.getId_user());
                for(Car car : cars) {
                    String stringResponse = objectMapper.writeValueAsString(converter.getCarDto(car));
                    resp.setContentType("application/json");
                    resp.getWriter().write(stringResponse + "\n");
                }
            }

        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String make = req.getParameter("make");
            String color = req.getParameter("color");
            String number = req.getParameter("number");
            if(make != null && color != null && number != null) {
                RestRequest resourceValues = new RestRequest(req.getPathInfo());
                carService.addCar(new Car.Builder().make(make).color(color).number(number).id_user(resourceValues.getId_user()).build());
            }
            else throw new IllegalArgumentException("Incorrect values");
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
