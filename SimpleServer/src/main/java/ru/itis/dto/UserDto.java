package ru.itis.dto;

import ru.itis.models.Car;

import java.util.List;

public class UserDto {

    private String lastName;
    private String firstName;
    private int age;
    private String city;
    private List<Car> cars;

    public UserDto() {
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
