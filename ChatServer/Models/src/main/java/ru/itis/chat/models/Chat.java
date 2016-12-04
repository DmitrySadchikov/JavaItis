package ru.itis.chat.models;

import java.util.List;

public class Chat {

    private Long id;
    private String name;
    private List<User> users;
    private List<Message> messages;
    private User creator;

    public static class Builder {

        private Long id;
        private String name;
        private List<User> users;
        private List<Message> messages;
        private User creator;

        public Builder id(Long id) {
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

        public Builder creator(User creator) {
            this.creator = creator;
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
        this.creator = builder.creator;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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

    public User getCreator() {
        return creator;
    }
}
