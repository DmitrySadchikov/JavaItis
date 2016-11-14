package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.UserService;

@Controller
public class LogoutController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView postLogout(@RequestHeader("token") String token) {

        if(!token.equals("")) {
            userService.deleteToken(token);
        }

        return new ModelAndView("redirect:/");
    }
}
