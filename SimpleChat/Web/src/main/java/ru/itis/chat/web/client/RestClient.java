package ru.itis.chat.web.client;


import org.springframework.web.client.RestTemplate;
import ru.itis.chat.dto.MessageDto;

public class RestClient {

    private RestTemplate restTemplate;

    public RestClient() {
        restTemplate = new RestTemplate();
    }

    public void sendMessage(MessageDto message) {
        restTemplate.postForObject("http://localhost:8080/messages", message, MessageDto.class);
    }

    public MessageDto[] getMessages() {
        return restTemplate.getForEntity("http://localhost:8080/messages", MessageDto[].class).getBody();
    }

}
