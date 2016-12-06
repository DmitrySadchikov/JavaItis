package ru.itis.chat.models;

import java.io.Serializable;

public class Token implements Serializable {

    private String value;

    public Token() {}

    public Token(String token) {
        this.value = token;
    }

    public String getToken() {
        return value;
    }

    public void setToken(String token) {
        this.value = token;
    }
}
