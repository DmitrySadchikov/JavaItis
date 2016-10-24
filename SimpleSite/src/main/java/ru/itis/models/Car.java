package ru.itis.models;

import com.google.common.base.MoreObjects;

public class Car {
    private int id;
    private String make;
    private String color;
    private int power;
    private int id_user;

    public static class Builder {
        private int id;
        private String make;
        private String color;
        private int power;
        private int id_user;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder make(String make) {
            this.make = make;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder power(int power) {
            this.power = power;
            return this;
        }

        public Builder id_user(int id_user) {
            this.id_user = id_user;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    public Car(Builder builder) {
        this.id = builder.id;
        this.make = builder.make;
        this.color = builder.color;
        this.power = builder.power;
        this.id_user = builder.id_user;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getColor() {
        return color;
    }

    public int getPower() {
        return power;
    }

    public int getId_user() { return  id_user; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Make", this.getMake())
                .add("Power", this.getPower())
                .add("Color", this.getColor())
                .toString();
    }
}
