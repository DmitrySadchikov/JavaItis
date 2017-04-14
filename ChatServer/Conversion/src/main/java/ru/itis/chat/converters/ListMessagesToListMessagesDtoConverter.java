package ru.itis.chat.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.models.Message;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListMessagesToListMessagesDtoConverter implements Converter<List<Message>, List<MessageDto>> {

    @Override
    public List<MessageDto> convert(List<Message> messages) {
        List<MessageDto> messageDtoList = new ArrayList<>();
        for(Message message : messages) {
            messageDtoList.add(new MessageDto.Builder()
                    .id(message.getId())
                    .text(message.getText())
                    .lastName(message.getSender().getLastName())
                    .firstName(message.getSender().getFirstName())
                    .date(message.getDate())
                    .build());
        }
        return messageDtoList;
    }
}
