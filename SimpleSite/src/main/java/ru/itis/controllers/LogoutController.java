package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView postLogout(@CookieValue("token") String token,
                                   HttpServletResponse response) {


        if(!token.equals("")) {
            Cookie killTokenCookie = new Cookie("token", null);
            killTokenCookie.setMaxAge(0);
            killTokenCookie.setPath("/");
            response.addCookie(killTokenCookie);
            userService.deleteToken(token);
        }

        return new ModelAndView("redirect:/");
    }
}
