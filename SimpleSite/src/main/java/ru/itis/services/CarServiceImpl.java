package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.dao.CarsDao;
import ru.itis.models.Car;

import java.util.List;

import static ru.itis.utils.Verifier.verifyCarExist;

@Component
public class CarServiceImpl implements CarService {

    @Autowired
    private CarsDao carsDao;

    public Car findCarById(int id) {
        return carsDao.find(id);
    }

    public void updateCar(Car car) {
        verifyCarExist(car.getId());
        this.carsDao.update(car);
    }

    public List<Car> getAll() {
        return this.carsDao.getAll();
    }

    public void deleteCar(int id) {
        verifyCarExist(id);
        this.carsDao.delete(id);
    }

    public void addCar(Car car) {
        this.carsDao.add(car);
    }
}
