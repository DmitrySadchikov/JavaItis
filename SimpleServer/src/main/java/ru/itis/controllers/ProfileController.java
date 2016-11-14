package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UserService;

import static ru.itis.converters.ModelConverter.getUserDto;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getProfile(@RequestHeader("token") String token) {

        if(!token.equals("")) {
            User user = userService.findUserByToken(token);
            return new ResponseEntity<UserDto>(getUserDto(user), HttpStatus.OK);
        }
        return new ResponseEntity<UserDto>(HttpStatus.UNAUTHORIZED);
    }

}
