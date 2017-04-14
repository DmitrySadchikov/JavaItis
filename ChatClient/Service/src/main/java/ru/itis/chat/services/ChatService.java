package ru.itis.chat.services;

import com.sun.istack.internal.Nullable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.web.connection.NetworkConnection;

import java.util.List;

public class ChatService {

    private static ChatService instance = new ChatService();

    private NetworkConnection networkConnection = NetworkConnection.getInstance();

    private ChatService() {}

    public ResponseEntity<ChatDto> getChat(@Nullable MultiValueMap<String, String> headers,
                                           Long chatId) {
        return networkConnection.exchange("/chats/" + chatId, HttpMethod.GET, headers, ChatDto.class);
    }

    public ResponseEntity<List<ChatDto>> getChats(@Nullable MultiValueMap<String, String> headers) {
        return networkConnection.exchange("/chats", HttpMethod.GET, headers, new ParameterizedTypeReference<List<ChatDto>>() {});
    }

    public ResponseEntity<ChatDto> postCreateChat(@Nullable MultiValueMap<String, String> headers) {
        return networkConnection.exchange("/chats", HttpMethod.POST, headers, ChatDto.class);
    }

    public static ChatService getInstance() {
        return instance;
    }
}
