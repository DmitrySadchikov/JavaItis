package ru.itis.chat.models;

public class CurrentUser {

    private Long id;
    private String lastName;
    private String firstName;
    private Token token;

    private static CurrentUser instance = new CurrentUser();

    private CurrentUser() {
        token = new Token();
    }

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
        return token.getToken();
    }

    public void setToken(String token) {
        this.token.setToken(token);
    }

    public void clear() {
        id = null;
        lastName = null;
        firstName = null;
        token = null;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
