package ru.itis.chat.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.itis.chat.converters.UserToUserDtoConverter;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.Token;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.TokenService;
import ru.itis.chat.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.itis.chat.secure.hash.Whirlpool.toHash;


@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserToUserDtoConverter converter;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDto> postLogin(@RequestHeader("login") String login,
                                             @RequestHeader("password") String password) {

        try {
            String hash = toHash(password);
            User user = userService.findUserByLogin(login);
            if (!hash.equals(user.getPassword())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else {

                Token token = new Token.Builder()
                        .user(user)
                        .build();

                tokenService.saveToken(token);
                List<String> tokens = new ArrayList<>();
                tokens.add(token.getToken());
                MultiValueMap<String, String> headers = new HttpHeaders();
                headers.put("token", tokens);
                return new ResponseEntity<>(converter.convert(user), headers, HttpStatus.ACCEPTED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
