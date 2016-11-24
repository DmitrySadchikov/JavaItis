package ru.itis.chat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chat.converters.UserToUserDtoConverter;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.UserService;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static ru.itis.chat.validators.UserValidator.verifyUserExistByLogin;
import static ru.itis.chat.web.utils.Whirlpool.toHash;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserToUserDtoConverter converter;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<UserDto> postRegistration(@RequestParam("login") String login,
                                                    @RequestParam("password") String password,
                                                    @RequestParam("last_name") String lastName,
                                                    @RequestParam("first_name") String firstName) {

        try {
            verifyUserExistByLogin(login);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException e) {

            String token = new BigInteger(130, new SecureRandom()).toString(32);

            User user = new User.Builder()
                    .login(login)
                    .password(toHash(password))
                    .lastName(lastName)
                    .firstName(firstName)
                    .token(token)
                    .build();

            userService.saveUser(user);
            List<String> tokens = new ArrayList<>();
            tokens.add(token);
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.put("token", tokens);

            return new ResponseEntity<>(converter.convert(user), headers, HttpStatus.CREATED);
        }
    }

}
