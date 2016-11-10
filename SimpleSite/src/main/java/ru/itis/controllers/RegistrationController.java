package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.User;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;

import static ru.itis.hash.Whirlpool.toHash;
import static ru.itis.utils.Verifier.verifyUserExist;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView getRegistration() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView postRegistration(@RequestParam("login") String login,
                                         @RequestParam("password") String password,
                                         @RequestParam("last_name") String lastName,
                                         @RequestParam("first_name") String firstName,
                                         @RequestParam("age") String age,
                                         @RequestParam("city") String city,
                                         HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        Integer integerAge = 0;
        if (!age.equals(""))
            integerAge = Integer.parseInt(age);

        try {
            verifyUserExist(login);
            modelAndView.addObject("error", "User is already exists");
            modelAndView.setViewName("registration");
            return modelAndView;
        } catch (IllegalArgumentException e) {

            String token = new BigInteger(130, new SecureRandom()).toString(32);

            userService.addUser(new User.Builder()
                    .login(login)
                    .password(toHash(password))
                    .lastName(lastName)
                    .firstName(firstName)
                    .age(integerAge)
                    .city(city)
                    .token(token)
                    .build());

            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);

            modelAndView.setViewName("profile");

            return modelAndView;
        }
    }

}
