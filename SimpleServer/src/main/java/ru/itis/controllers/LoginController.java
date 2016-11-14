package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.UserDto;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;

import static ru.itis.hash.Whirlpool.toHash;

@RestController
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
    public ResponseEntity postLogin(@RequestParam("login") String login,
                                             @RequestParam("password") String password,
                                             HttpServletResponse response) {

        String hash = toHash(password);

        try {
            if (!hash.equals(userService.getPassword(login))) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                String token = new BigInteger(130, new SecureRandom()).toString(32);

                response.setHeader("token", token);

                userService.setToken(login, token);
                return new ResponseEntity<UserDto>(HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

    }
}
