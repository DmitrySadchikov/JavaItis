package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.Car;
import ru.itis.models.User;
import ru.itis.services.CarService;
import ru.itis.services.UserService;

import static ru.itis.utils.Verifier.verifyCarExist;

@Controller
public class AddCarController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/addcar", method = RequestMethod.GET)
    public ModelAndView getAddCar(@CookieValue("token") String token) {
        ModelAndView modelAndView = new ModelAndView();

        if(!token.equals("")) {
            User user = userService.findUserByToken(token);
            modelAndView.addObject("user", user);
        }

        modelAndView.setViewName("car");
        return modelAndView;
    }

    @RequestMapping(value = "/addcar", method = RequestMethod.POST)
    public ModelAndView postAddCar(@RequestParam("make") String make,
                                   @RequestParam("number") String number,
                                   @RequestParam("color") String color,
                                   @CookieValue("token") String token) {

        ModelAndView modelAndView = new ModelAndView();

        try {
            verifyCarExist(number);
            if(!token.equals("")) {
                int id_user = userService.findIdByToken(token);

                carService.addCar(new Car.Builder()
                        .make(make)
                        .number(number)
                        .color(color)
                        .id_user(id_user)
                        .build());


            }
            modelAndView.setViewName("car");
            return modelAndView;
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("error", "The car with this number already exists");
            modelAndView.setViewName("car");
            return modelAndView;
        }

    }
}
