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
import ru.itis.chat.models.Token;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.TokenService;
import ru.itis.chat.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.itis.chat.validators.UserValidator.verifyUserExistByLogin;
import static ru.itis.chat.secure.hash.Whirlpool.toHash;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

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

            try {
                User user = new User.Builder()
                        .login(login)
                        .password(toHash(password))
                        .lastName(lastName)
                        .firstName(firstName)
                        .build();

                userService.saveUser(user);

                Token token = new Token.Builder()
                        .user(userService.findUserByLogin(user.getLogin()))
                        .build();

                tokenService.saveToken(token);
                List<String> tokens = new ArrayList<>();
                tokens.add(token.getToken());
                MultiValueMap<String, String> headers = new HttpHeaders();
                headers.put("token", tokens);
                user.setId(userService.findIdByToken(token.getToken()));

                return new ResponseEntity<>(converter.convert(user), headers, HttpStatus.CREATED);
            } catch (IllegalArgumentException e1) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
    }

}
