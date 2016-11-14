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
import ru.itis.models.User;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;

import static ru.itis.converters.ModelConverter.getUserDto;
import static ru.itis.hash.Whirlpool.toHash;
import static ru.itis.utils.Verifier.verifyUserExist;

@RestController
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
    public ResponseEntity<UserDto> postRegistration(@RequestParam("login") String login,
                                                    @RequestParam("password") String password,
                                                    @RequestParam("last_name") String lastName,
                                                    @RequestParam("first_name") String firstName,
                                                    @RequestParam("age") String age,
                                                    @RequestParam("city") String city,
                                                    HttpServletResponse response) {

        Integer integerAge = 0;
        if (!age.equals(""))
            integerAge = Integer.parseInt(age);

        try {
            verifyUserExist(login);
            return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {

            String token = new BigInteger(130, new SecureRandom()).toString(32);

            User user = new User.Builder()
                    .login(login)
                    .password(toHash(password))
                    .lastName(lastName)
                    .firstName(firstName)
                    .age(integerAge)
                    .city(city)
                    .token(token)
                    .build();

            userService.addUser(user);
            response.setHeader("token", token);

            return new ResponseEntity<UserDto>(getUserDto(user), HttpStatus.CREATED);
        }
    }

}
