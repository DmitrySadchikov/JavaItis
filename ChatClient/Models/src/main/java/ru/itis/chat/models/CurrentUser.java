package ru.itis.chat.models;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Map;

public class CurrentUser implements Serializable {

    private Long id;
    private String lastName;
    private String firstName;
    private String token;
    private Map<Long, String> chats;

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

    public Map<Long, String> getChats() {
        return chats;
    }

    public void setChats(Map<Long, String> chats) {
        this.chats = chats;
    }

    public void clear() {
        id = null;
        lastName = null;
        firstName = null;
        token = null;
    }

    private Object readResolve() throws ObjectStreamException {
        instance.setId(getId());
        instance.setLastName(getLastName());
        instance.setFirstName(getFirstName());
        instance.setToken(getToken());
        return instance;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

}
