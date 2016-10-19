package ru.itis.models;

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

}
