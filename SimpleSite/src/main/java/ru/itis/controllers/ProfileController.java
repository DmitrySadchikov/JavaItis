package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.User;
import ru.itis.services.UserService;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfile(@CookieValue("token") String token) {

        ModelAndView modelAndView = new ModelAndView();
        if(!token.equals("")) {
            User user = userService.findUserByToken(token);
            modelAndView.addObject("user", user);
        }
        modelAndView.setViewName("profile");

        return modelAndView;
    }

}
