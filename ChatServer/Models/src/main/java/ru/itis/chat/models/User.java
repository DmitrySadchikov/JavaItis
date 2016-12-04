package ru.itis.chat.models;

public class User {

    private Long id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;

    public static class Builder {
        private Long id;
        private String login;
        private String password;
        private String lastName;
        private String firstName;

        public Builder id(Long id) {
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

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return getLastName() + " " + getFirstName();
    }
}
