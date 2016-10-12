package ru.itis.dao;

import ru.itis.DaoSupportFactory;
import ru.itis.models.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarsDaoJdbcImpl implements CarsDao {

    private Connection connection;

    public CarsDaoJdbcImpl() {
        this.connection = DaoSupportFactory.getInstance().getConnection();
    }

    public List<Car> getAll() {

        try {
            List<Car> cars = new ArrayList<Car>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM car_users");
            while (resultSet.next()) {
                Car c = new Car(resultSet.getInt("id"), resultSet.getString("car_make"),
                        resultSet.getInt("mileage"), resultSet.getInt("power"));
                cars.add(c);
            }
            return cars;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Car find(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars WHERE id=" + id);
            resultSet.next();
            Car c = new Car(resultSet.getInt("id"), resultSet.getString("car_make"),
                    resultSet.getInt("mileage"), resultSet.getInt("power"));
            return c;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("DELETE * FROM cars WHERE id=" + id);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(Car car) {
        // описать случай, когда нет такой машины

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("UPDATE cars SET car_make=" + car.getMake()
                    + ", mileage=" + car.getMileage() + ", power=" + car.getPower()
                    + " WHERE id=" + car.getId());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void add(Car car) {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO cars (car_make, mileage, power)" +
                    " VALUES (" + car.getMake() + ", " + car.getMileage() + ", "
                    + car.getPower() + ");");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
