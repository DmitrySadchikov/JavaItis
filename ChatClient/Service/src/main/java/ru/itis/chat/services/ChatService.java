package ru.itis.chat.services;

import com.sun.istack.internal.Nullable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.web.connection.NetworkConnection;

import java.util.Map;

public class ChatService {

    private static ChatService instance = new ChatService();

    private NetworkConnection networkConnection = NetworkConnection.getInstance();

    private ChatService() {}

    public ResponseEntity<ChatDto> getChat(@Nullable MultiValueMap<String, String> headers, Long chatId) {
        return networkConnection.exchange("/chats/" + chatId, HttpMethod.GET, headers, ChatDto.class);
    }

    public ResponseEntity<ChatDto> postCreateChat(@Nullable MultiValueMap<String, String> headers,
                                                  Map<String, String> parameters) {
        return networkConnection.exchange("/chats", HttpMethod.POST, headers, parameters, ChatDto.class);
    }

    public static ChatService getInstance() {
        return instance;
    }
}
