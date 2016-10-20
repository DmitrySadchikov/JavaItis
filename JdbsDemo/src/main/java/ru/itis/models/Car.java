package ru.itis.models;

import com.google.common.base.MoreObjects;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Make", this.getMake())
                .add("Power", this.getPower())
                .add("Mileage", this.getMileage())
                .toString();
    }
}
