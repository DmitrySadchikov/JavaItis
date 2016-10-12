package ru.itis.dao;

import ru.itis.models.Car;
import java.util.List;

public interface CarsDao {

    void find(int id);
    List<Car> getAll();
    void delete(int id);
    void update(Car car);
    void add(Car car);
}
