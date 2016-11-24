package ru.itis.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.chat.dao.MessagesDao;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.models.Message;
import ru.itis.chat.services.interfaces.MessageService;
import ru.itis.chat.web.client.RestClient;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessagesDao messagesDao;

    private ExecutorService executorService;
    private RestClient restClient;

    public MessageServiceImpl() {
        executorService = Executors.newSingleThreadExecutor(); // ??
        //TODO: вынести в сервисы
        restClient = new RestClient();
    }

    private void messagesHandle() {
        Runnable handleMessagesTask = () -> {
            while (true) {
                MessageDto[] messages = restClient.getMessages();
                for(MessageDto message : messages) {
                    System.out.println(message.getLastName() + " " + message.getFirstName() + ": " +
                    message.getMessage());
                }
            }
        };

        executorService.submit(handleMessagesTask);
    }

    public void messageProcessing() {
        messagesHandle();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String text = scanner.nextLine();
        }
    }

    @Override
    public List<Message> findAll(long chatId) {
        return this.messagesDao.findAll(chatId);
    }
}
