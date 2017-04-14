package ru.itis.chat.converters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.chat.dto.FullChatDto;
import ru.itis.chat.models.Chat;

@Component
public class ChatToFullChatDtoConverter implements Converter<Chat, FullChatDto> {

    @Autowired
    private UserToUserDtoConverter usersConverter;
    @Autowired
    private ListUsersToListUsersDtoConverter listUsersConverter;
    @Autowired
    private ListMessagesToListMessagesDtoConverter listMessagesConverter;

    @Override
    public FullChatDto convert(Chat chat) {
        return new FullChatDto.Builder()
                .id(chat.getId())
                .name(chat.getName())
                .users(listUsersConverter.convert(chat.getUsers()))
                .messages(listMessagesConverter.convert(chat.getMessages()))
                .creator(usersConverter.convert(chat.getCreator()))
                .build();
    }
}
