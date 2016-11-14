package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.itis.converters.ModelConverter.getUserDto;

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam("age") String age) {

        if(age != null) {
            User user = userService.findUserByAge(Integer.parseInt(age));
            List<UserDto> userDtoList = new ArrayList<UserDto>();
            userDtoList.add(getUserDto(user));
            return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
        }
        else {
            List<User> users = userService.getAll();
            List<UserDto> userDtoList = new ArrayList<UserDto>();
            for (User user : users) {
                userDtoList.add(getUserDto(user));
            }
            return new ResponseEntity<List<UserDto>>(userDtoList, HttpStatus.OK);
        }
    }

}
