package ru.itis.chat.models;

public class CurrentUser {

    private Long id;
    private String lastName;
    private String firstName;
    private String token;

    private static CurrentUser instance = new CurrentUser();

    private CurrentUser() {}

    public static CurrentUser getInstance() {
        return instance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
