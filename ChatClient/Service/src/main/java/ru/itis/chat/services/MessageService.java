package ru.itis.chat.services;

import com.sun.istack.internal.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.web.connection.NetworkConnection;

import java.util.List;

public class MessageService {

    private static MessageService instance = new MessageService();

    private NetworkConnection networkConnection = NetworkConnection.getInstance();

    private MessageService() {}

    public ResponseEntity<MessageDto> sendMessage(@Nullable MultiValueMap<String, String> headers, Long chatId) {
        return networkConnection.exchange("/chats/" + chatId + "/messages", HttpMethod.POST, headers, MessageDto.class);
    }

    public ResponseEntity<List<MessageDto>> getAllMessages(@Nullable MultiValueMap<String, String> headers, Long chatId) {
        return networkConnection.exchange("/chats/" + chatId + "/messages/all", HttpMethod.GET, headers,
                new ParameterizedTypeReference<List<MessageDto>>() {});
    }

    public static MessageService getInstance() {
        return instance;
    }
}
