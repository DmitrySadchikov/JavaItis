package ru.itis.models;

public class Car {
    private String make;
    private int mileage;
    private int power;

    public Car(String make, int mileage, int power) {
        this.make = make;
        this.mileage = mileage;
        this.power = power;
    }

    public String getMake() {
        return make;
    }

    public int getMileage() {
        return mileage;
    }

    public int getPower() {
        return power;
    }
}
