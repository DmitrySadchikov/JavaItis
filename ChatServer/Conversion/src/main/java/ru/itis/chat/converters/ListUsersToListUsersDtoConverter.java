package ru.itis.chat.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.chat.dto.UserDto;
import ru.itis.chat.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListUsersToListUsersDtoConverter implements Converter<List<User>, List<UserDto>> {
    @Override
    public List<UserDto> convert(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : users) {
            userDtoList.add(new UserDto.Builder()
                    .id(user.getId())
                    .lastName(user.getLastName())
                    .firstName(user.getFirstName())
                    .build());
        }
        return userDtoList;
    }
}
