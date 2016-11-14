package ru.itis.converters;

import ru.itis.dto.CarDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Car;
import ru.itis.models.User;

public class ModelConverter {

    public static UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setAge(user.getAge());
        userDto.setCity(user.getCity());
        userDto.setCars(user.getCars());
        return userDto;
    }

    public static CarDto getCarDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setMake(car.getMake());
        carDto.setColor(car.getColor());
        carDto.setNumber(car.getNumber());
        return carDto;
    }

}
