package ru.itis.chat.dto;

import java.util.Date;

public class MessageDto {

    private String lastName;
    private String firstName;
    private Date date;
    private String text;

    public MessageDto() {}

    public MessageDto(Builder builder) {
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.date = builder.date;
        this.text = builder.text;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static class Builder {

        private String lastName;
        private String firstName;
        private Date date;
        private String text;

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public MessageDto build() {
            return new MessageDto(this);
        }
    }

}
