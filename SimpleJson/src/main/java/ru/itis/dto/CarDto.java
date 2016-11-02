package ru.itis.dto;

public class CarDto {

    private String make;
    private String color;
    private String number;


    public CarDto() {
    }

    public String getMake() {
        return make;
    }

    public String getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
