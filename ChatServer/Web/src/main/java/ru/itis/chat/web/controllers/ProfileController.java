package ru.itis.chat.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chat.converters.UserToUserDtoConverter;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.UserService;

@RestController
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserToUserDtoConverter converter;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getProfile(@RequestHeader("token") String token) {

        if(!token.equals("")) {
            User user = userService.findUserByToken(token);
            return new ResponseEntity<>(converter.convert(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
