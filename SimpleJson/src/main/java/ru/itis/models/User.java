package ru.itis.models;

import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private int age;
    private String city;
    private String token;
    private List<Car> cars;


    public static class Builder {
        private int id;
        private String login;
        private String password;
        private String lastName;
        private String firstName;
        private int age;
        private String city;
        private String token;
        private List<Car> cars;


        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder cars(List<Car> cars) {
            this.cars = cars;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.age = builder.age;
        this.city = builder.city;
        this.token = builder.token;
        this.cars = builder.cars;
    }



    public int getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public String toString() {
        String result;
        if(this.age != 0) {
            result = getLastName() + " " + getFirstName()  +
                    ", " + getAge() + ", " + getCity();
        }
        else
            result = getLastName() + " " + getFirstName()  +
                    ", " + getCity();
        if(cars != null)
            for(Car car : cars) {
                result += " {" + car + "} ";
            }
        return result;
    }
}
