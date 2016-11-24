package ru.itis.chat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.chat.services.interfaces.UserService;

@RestController
public class LogoutController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity postLogout(@RequestHeader("token") String token) {

        if(!token.equals("")) {
            userService.deleteToken(token);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
