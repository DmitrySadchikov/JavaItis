package ru.itis.models;

public class Car {
    private int id;
    private String make;
    private int mileage;
    private int power;

    public Car(int id, String make, int mileage, int power) {
        this.id = id;
        this.make = make;
        this.mileage = mileage;
        this.power = power;
    }

    public int getId() {
        return id;
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
