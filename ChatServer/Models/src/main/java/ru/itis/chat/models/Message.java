package ru.itis.chat.models;

import java.util.Date;

public class Message {

    private Long id;
    private String text;
    private Date date;
    private User sender;

    public static class Builder {

        private Long id;
        private String text;
        private Date date = new Date();
        private User sender;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder sender(User userId) {
            this.sender = userId;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

    public Message(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.date = builder.date;
        this.sender = builder.sender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public User getSender() {
        return sender;
    }
}
