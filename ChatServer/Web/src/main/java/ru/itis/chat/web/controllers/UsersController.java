package ru.itis.chat.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chat.converters.UserToUserDtoConverter;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserToUserDtoConverter converter;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getUsers() {

            List<User> users = userService.findAll();
            List<UserDto> userDtoList = new ArrayList<>();
            for (User user : users) {
                userDtoList.add(converter.convert(user));
            }
            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

}
