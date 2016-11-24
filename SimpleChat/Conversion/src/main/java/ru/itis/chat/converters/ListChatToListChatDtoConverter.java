package ru.itis.chat.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.models.Chat;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListChatToListChatDtoConverter implements Converter<List<Chat>, List<ChatDto>>{
    @Override
    public List<ChatDto> convert(List<Chat> chats) {
        List<ChatDto> chatDtoList = new ArrayList<>();
        for(Chat chat : chats) {
            chatDtoList.add(new ChatDto.Builder()
                    .id(chat.getId())
                    .name(chat.getName())
                    .build());
        }
        return chatDtoList;
    }
}
