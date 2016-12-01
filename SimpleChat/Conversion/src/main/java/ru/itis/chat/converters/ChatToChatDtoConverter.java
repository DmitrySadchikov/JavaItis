package ru.itis.chat.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.models.Chat;

@Component
public class ChatToChatDtoConverter implements Converter<Chat, ChatDto> {
    @Override
    public ChatDto convert(Chat chat) {
        return new ChatDto.Builder()
                .id(chat.getId())
                .name(chat.getName())
                .build();
    }
}
