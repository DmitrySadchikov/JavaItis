package ru.itis.dao;

import ru.itis.models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDaoJdbcImpl implements CarsDao {

    private Connection connection;

    //language=SQL
    private static final String SQL_ALL_CARS = "SELECT * FROM cars";
    //language=SQL
    private static final String SQL_FIND_CARS = "SELECT * FROM cars WHERE id = ?";
    //language=SQL
    private static final String SQL_DELETE_CARS = "DELETE FROM cars WHERE id = ?";
    //language=SQL
    private static final String SQL_UPDATE_CARS = "UPDATE cars SET make = ?, " +
            "mileage = ?, power = ?, WHERE id = ?;";
    //language=SQL
    private static final String SQL_ADD_CARS = "INSERT INTO cars (make, mileage, power)"
            + " VALUES (?, ?, ?);";

    public CarsDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Car> getAll() {

        try {
            List<Car> cars = new ArrayList<Car>();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_ALL_CARS);
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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CARS);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_CARS);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(Car car) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CARS);
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setInt(2, car.getMileage());
            preparedStatement.setInt(3, car.getPower());
            preparedStatement.setInt(4, car.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void add(Car car) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_CARS);
            preparedStatement.setString(1, car.getMake());
            preparedStatement.setInt(2, car.getMileage());
            preparedStatement.setInt(3, car.getPower());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
