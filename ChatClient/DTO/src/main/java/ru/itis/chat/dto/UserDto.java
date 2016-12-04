package ru.itis.chat.dto;

public class UserDto {

    private Long id;
    private String lastName;
    private String firstName;

    private static UserDto instance = new UserDto();

    private UserDto() {}

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

    public static UserDto getInstance() {
        return instance;
    }
}
