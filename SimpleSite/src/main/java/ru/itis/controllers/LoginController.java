package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;

import static ru.itis.hash.Whirlpool.toHash;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView postLogin(@RequestParam("login") String login,
                                  @RequestParam("password") String password,
                                  HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        String hash = toHash(password);

        if (!hash.equals(userService.getPassword(login))) {
            modelAndView.addObject("error", "Incorrect password");
            modelAndView.setViewName("login");
        } else {
            String token = new BigInteger(130, new SecureRandom()).toString(32);
            Cookie cookie = new Cookie("token", token);

            response.addCookie(cookie);
            userService.setToken(login, token);
            modelAndView.setViewName("redirect:/profile");
        }
        return modelAndView;
    }
}
