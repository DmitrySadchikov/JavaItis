package ru.itis.models;

import com.google.common.base.MoreObjects;

public class User {
    private int id;
    private String name;
    private int age;
    private String password;
    private String city;

    public User(int id, String name, int age, String password, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.password = password;
        this.city = city;
    }

    public User(String name, int age, String password, String city) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Name", this.getName())
                .add("Age", this.getAge())
                .add("City", this.getCity())
                .toString();
    }
}
