package ru.itis.chat.models;

public class User {

    private long id;
    private String login;
    private String password;
    private String lastName;
    private String firstName;
    private String token;


    public static class Builder {
        private long id;
        private String login;
        private String password;
        private String lastName;
        private String firstName;
        private String token;


        public Builder id(long id) {
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

        public Builder token(String token) {
            this.token = token;
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
        this.token = builder.token;
    }

    public long getId() {
        return id;
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

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return getLastName() + " " + getFirstName();
    }
}
