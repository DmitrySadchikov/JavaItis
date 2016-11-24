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
import ru.itis.chat.services.interfaces.UserService;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static ru.itis.chat.web.utils.Whirlpool.toHash;


@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserToUserDtoConverter converter;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDto> postLogin(@RequestParam("login") String login,
                                             @RequestParam("password") String password) {

        String hash = toHash(password);

        try {
            if (!hash.equals(userService.findPasswordByLogin(login))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                String token = new BigInteger(130, new SecureRandom()).toString(32);

                List<String> tokens = new ArrayList<>();
                tokens.add(token);
                userService.saveToken(login, token);
                MultiValueMap<String, String> headers = new HttpHeaders();
                headers.put("token", tokens);
                return new ResponseEntity<>(converter.convert(userService.findUserByToken(token)), headers, HttpStatus.CREATED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
