package ru.itis.chat.models;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Token {

    private Long id;
    private String token;
    private User user;

    public Token(Builder builder) {
        this.id = builder.id;
        this.token = builder.token;
        this.user = builder.user;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public static class Builder {

        private Long id;
        private String token = new BigInteger(130, new SecureRandom()).toString(32);
        private User user;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Token build() {
            return new Token(this);
        }
    }
}
