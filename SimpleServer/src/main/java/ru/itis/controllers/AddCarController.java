package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.CarDto;
import ru.itis.models.Car;
import ru.itis.models.User;
import ru.itis.services.CarService;
import ru.itis.services.UserService;

import static ru.itis.converters.ModelConverter.getCarDto;
import static ru.itis.utils.Verifier.verifyCarExist;

@RestController
public class AddCarController {

    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/addcar", method = RequestMethod.GET)
    public ModelAndView getAddCar(@RequestHeader("token") String token) {
        ModelAndView modelAndView = new ModelAndView();

        if(!token.equals("")) {
            User user = userService.findUserByToken(token);
            modelAndView.addObject("user", user);
        }

        modelAndView.setViewName("car");
        return modelAndView;
    }

    @RequestMapping(value = "/addcar", method = RequestMethod.POST)
    public ResponseEntity<CarDto> postAddCar(@RequestParam("make") String make,
                                             @RequestParam("number") String number,
                                             @RequestParam("color") String color,
                                             @RequestHeader("token") String token) {

        try {
            verifyCarExist(number);
            int id_user = userService.findIdByToken(token);

            Car car = new Car.Builder()
                    .make(make)
                    .number(number)
                    .color(color)
                    .id_user(id_user)
                    .build();

            carService.addCar(car);

            return new ResponseEntity<CarDto>(getCarDto(car), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<CarDto>(HttpStatus.BAD_REQUEST);
        }

    }
}
