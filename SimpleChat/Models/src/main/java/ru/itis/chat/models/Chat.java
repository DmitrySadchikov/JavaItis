package ru.itis.chat.models;

import java.util.List;

public class Chat {

    private long id;
    private String name;
    private List<User> users;
    private List<Message> messages;


    public static class Builder {

        private long id;
        private String name;
        private List<User> users;
        private List<Message> messages;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder users(List<User> users) {
            this.users = users;
            return this;
        }

        public Builder messages(List<Message> messages) {
            this.messages = messages;
            return this;
        }

        public Chat build() {
            return new Chat(this);
        }
    }

    public Chat(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.users = builder.users;
        this.messages = builder.messages;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
