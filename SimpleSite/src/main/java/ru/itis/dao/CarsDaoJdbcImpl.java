package ru.itis.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.itis.models.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarsDaoJdbcImpl implements CarsDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    private static final String SQL_ALL_CARS = "SELECT * FROM cars";
    //language=SQL
    private static final String SQL_FIND_CARS = "SELECT * FROM cars WHERE id = :id";
    //language=SQL
    private static final String SQL_DELETE_CARS = "DELETE FROM cars WHERE id = :id";
    //language=SQL
    private static final String SQL_UPDATE_CARS = "UPDATE cars SET make = :make, " +
            "color = :color, number_ = :number_ WHERE id = :id;";
    //language=SQL
    private static final String SQL_ADD_CARS = "INSERT INTO cars (make, color, number_, id_user)"
            + " VALUES (:make, :color, :number_, :id_user);";

    public CarsDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Car> getAll() {
        return namedParameterJdbcTemplate.query(SQL_ALL_CARS, new RowMapper<Car>() {
            public Car mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Car.Builder()
                        .make(resultSet.getString("make"))
                        .color(resultSet.getString("color"))
                        .number(resultSet.getString("number_"))
                        .build();
            }
        });
    }

    public Car find(int id) {
        Map<String, Integer> namedParameters = new HashMap<String, Integer>();
        namedParameters.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(SQL_FIND_CARS, namedParameters, new RowMapper<Car>() {
            public Car mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Car.Builder()
                        .make(resultSet.getString("make"))
                        .color(resultSet.getString("color"))
                        .number(resultSet.getString("number_")).build();
            }
        });
    }

    public void delete(int id) {
        Map<String, Integer> namedParameters = new HashMap<String, Integer>();
        namedParameters.put("id", id);
        namedParameterJdbcTemplate.update(SQL_DELETE_CARS, namedParameters);
    }

    public void update(Car car) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("make", car.getMake());
        namedParameters.put("color", car.getColor());
        namedParameters.put("number_", car.getNumber());
        namedParameters.put("id", car.getId());
        namedParameterJdbcTemplate.update(SQL_UPDATE_CARS, namedParameters);
    }

    public void add(Car car) {
        Map<String, Object> namedParameters = new HashMap<String, Object>();
        namedParameters.put("make", car.getMake());
        namedParameters.put("color", car.getColor());
        namedParameters.put("number_", car.getNumber());
        namedParameters.put("id_user", car.getId_user());
        namedParameterJdbcTemplate.update(SQL_ADD_CARS, namedParameters);
    }
}
