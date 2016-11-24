package ru.itis.chat.models;

import java.sql.Date;
import java.sql.Time;

public class Message {

    private long id;
    private String text;
    private Time time;
    private Date date;
    private long userId;

    public static class Builder {

        private long id;
        private String text;
        private Time time;
        private Date date;
        private long userId;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder time(Time time) {
            this.time = time;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }

    public Message(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.time = builder.time;
        this.date = builder.date;
        this.userId = builder.userId;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Time getTime() {
        return time;
    }

    public Date getDate() {
        return date;
    }

    public long getUserId() {
        return userId;
    }
}
