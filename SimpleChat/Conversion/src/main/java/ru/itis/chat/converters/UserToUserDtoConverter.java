package ru.itis.chat.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.User;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto>{
    @Override
    public UserDto convert(User user) {
        return new UserDto.Builder()
                .id(user.getId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .build();
    }
}
