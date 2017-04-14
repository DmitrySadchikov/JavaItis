package ru.itis.chat.dto;

import java.util.List;

public class FullChatDto {

    private Long id;
    private String name;
    private List<UserDto> users;
    private List<MessageDto> messages;
    private UserDto creator;

    public FullChatDto() {}

    public FullChatDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.users = builder.users;
        this.messages = builder.messages;
        this.creator = builder.creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    public UserDto getCreator() {
        return creator;
    }

    public void setCreator(UserDto creator) {
        this.creator = creator;
    }

    public static class Builder {

        private Long id;
        private String name;
        private List<UserDto> users;
        private List<MessageDto> messages;
        private UserDto creator;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder users(List<UserDto> users) {
            this.users = users;
            return this;
        }

        public Builder messages(List<MessageDto> messages) {
            this.messages = messages;
            return this;
        }

        public Builder creator(UserDto creator) {
            this.creator = creator;
            return this;
        }

        public FullChatDto build() {
            return new FullChatDto(this);
        }
    }
}
