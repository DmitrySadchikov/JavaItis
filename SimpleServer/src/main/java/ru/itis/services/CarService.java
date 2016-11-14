package ru.itis.services;

import ru.itis.models.Car;

import java.util.List;

public interface CarService {
    Car findCarById(int id);
    void updateCar(Car car);

    List<Car> getAll();
    void deleteCar(int id);
    void addCar(Car car);
}
