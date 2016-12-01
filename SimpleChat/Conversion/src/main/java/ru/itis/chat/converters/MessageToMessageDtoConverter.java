package ru.itis.chat.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.models.Message;

@Component
public class MessageToMessageDtoConverter implements Converter<Message, MessageDto> {

    @Override
    public MessageDto convert(Message message) {
        return new MessageDto.Builder()
                .text(message.getText())
                .lastName(message.getSender().getLastName())
                .firstName(message.getSender().getFirstName())
                .date(message.getDate())
                .build();
    }

}
