package ru.itis.chat.models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class CurrentUser implements Serializable {

    private Long id;
    private String lastName;
    private String firstName;
    private String token;
    private Long idChat;
    //private Map<Long, String> chats;

    private static CurrentUser instance = new CurrentUser();

    private CurrentUser() {
        //chats = new HashMap<>(50);
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
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    /*public Map<Long, String> getChats() {
        return chats;
    }

    public void setChats(Map<Long, String> chats) {
        this.chats = chats;
    }

    public void addChat(Long id, String name) {
        chats.put(id, name);
    }*/

    public void clear() {
        try (FileOutputStream fileOutputStream = new FileOutputStream("Service/src/main/resources/CurrentUser.ser")) {
            fileOutputStream.write(("").getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        id = null;
        lastName = null;
        firstName = null;
        token = null;
        idChat = null;
        //chats = null;
    }

    private Object readResolve() throws ObjectStreamException {
        instance.setId(getId());
        instance.setLastName(getLastName());
        instance.setFirstName(getFirstName());
        instance.setToken(getToken());
        //instance.setChats(getChats());
        instance.setIdChat(getIdChat());
        return instance;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

}
