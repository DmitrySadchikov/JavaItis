package ru.itis.chat.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.chat.converters.MessageToMessageDtoConverter;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.models.Message;
import ru.itis.chat.models.User;
import ru.itis.chat.services.interfaces.MessageService;
import ru.itis.chat.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessagesController {


    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageToMessageDtoConverter messageConverter;


    @RequestMapping(value = "/chats/{chatId}/messages", method = RequestMethod.POST)
    public ResponseEntity<MessageDto> sendMessage(@RequestHeader("token") String token,
                                                  @RequestHeader("text") String text,
                                                  @PathVariable("chatId") Long chatId) {
        Message message = new Message.Builder()
                .text(text)
                .sender(userService.findUserByToken(token))
                .build();
            messageService.save(message, chatId);
        return new ResponseEntity<>(messageConverter.convert(message), HttpStatus.CREATED);
    }

    //Long Polling
    @RequestMapping(value = "/chats/{chatId}/messages", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDto>> getNewMessages(@RequestHeader("token") String token,
                                                           @PathVariable("chatId") Long chatId) {
        User user = userService.findUserByToken(token);
        synchronized (messageService.getNewMessages()) {
            while (messageService.findAllLastMessages(user.getId(), chatId).isEmpty()) {
                try {
                    messageService.getNewMessages().wait();
                } catch (InterruptedException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

        List<MessageDto> result = new ArrayList<>(messageService.getNewMessages());
        messageService.findAllLastMessages(user.getId(), chatId).clear();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/chats/{chatId}/messages/all", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDto>> getAllMessages(@PathVariable("chatId") Long chatId) {
        return new ResponseEntity<>(messageService.findAll(chatId), HttpStatus.OK);
    }
}
