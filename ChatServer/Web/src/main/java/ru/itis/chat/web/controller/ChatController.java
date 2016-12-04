package ru.itis.chat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.chat.converters.ChatToChatDtoConverter;
import ru.itis.chat.converters.ListChatToListChatDtoConverter;
import ru.itis.chat.converters.UserToUserDtoConverter;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.Chat;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.ChatService;
import ru.itis.chat.services.interfaces.UserService;
import ru.itis.chat.services.interfaces.UtilService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Autowired
    private UtilService utilService;
    @Autowired
    private ListChatToListChatDtoConverter listChatConverter;
    @Autowired
    private ChatToChatDtoConverter chatConverter;
    @Autowired
    private UserToUserDtoConverter userConverter;

    @RequestMapping(value = "/chats", method = RequestMethod.GET)
    public ResponseEntity<List<ChatDto>> getChats(@RequestHeader("token") String token) {

        return new ResponseEntity<>(listChatConverter.convert(chatService.findAll(token)), HttpStatus.OK);
    }

    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    public ResponseEntity<ChatDto> createChat(@RequestHeader("token") String token,
                                              @RequestParam("name") String name) {

        User user = userService.findUserByToken(token);
        List<User> users = new ArrayList<>();
        users.add(user);
        Chat chat = new Chat.Builder()
                .name(name)
                .users(users)
                .creator(user)
                .build();
        chatService.save(chat);
        chat.setId(chatService.findIdByName(chat.getName()));
        utilService.saveUserInChat(chat.getId(), user.getId());
        return new ResponseEntity<>(chatConverter.convert(chat), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/chats/{chatId}", method = RequestMethod.POST)
    public ResponseEntity<UserDto> addUserInChat(@RequestParam("user_id") Long userId,
                                                 @PathVariable("chatId") Long chatId) {
        utilService.saveUserInChat(chatId, userId);
        return new ResponseEntity<>(userConverter.convert(userService.findUserById(userId)),
                HttpStatus.ACCEPTED);
    }
}
