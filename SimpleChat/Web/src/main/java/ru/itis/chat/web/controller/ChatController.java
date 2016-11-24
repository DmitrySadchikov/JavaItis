package ru.itis.chat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.chat.converters.ListChatToListChatDtoConverter;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.ChatService;
import ru.itis.chat.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Autowired
    private ListChatToListChatDtoConverter converter;

    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public ResponseEntity<List<ChatDto>> getChats(@RequestHeader("token") String token) {

        return new ResponseEntity<>(converter.convert(chatService.findAll(token)), HttpStatus.OK);
    }

    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    public ResponseEntity<Long> createChat(@RequestHeader("token") String token,
                                              @RequestParam("name") String name) {

        List<User> users = new ArrayList<>();
        users.add(userService.findUserByToken(token));
        Chat chat = new Chat.Builder()
                .name(name)
                .users(users)
                .build();
        chatService.save(chat);
        return new ResponseEntity<>(chatService.findIdByName(name), HttpStatus.CREATED);
    }



}
