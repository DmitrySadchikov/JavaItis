package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.dto.MessageDto;
import ru.itis.chat.gui.scenes.SceneManager;
import ru.itis.chat.models.CurrentUser;
import ru.itis.chat.services.ChatService;
import ru.itis.chat.services.MessageService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController implements Initializable{

    @FXML
    private JFXButton send;
    @FXML
    private JFXTextField textField;
    @FXML
    private JFXButton back;
    @FXML
    private Text chatName;
    @FXML
    private Pane pane;
    @FXML
    private TextArea textArea;
    private ChatDto chat;

    private SceneManager sceneManager = SceneManager.getInstance();
    private ChatService chatService = ChatService.getInstance();
    private MessageService messageService = MessageService.getInstance();

    @FXML
    private void backClick() {
        sceneManager.showProfileScene();
    }

    @FXML
    private void sendClick() {
        String text = textField.getText();
        if(!text.equals("")) {
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("token", CurrentUser.getInstance().getToken());
            headers.add("text", text);
            ResponseEntity<MessageDto> responseEntity = messageService.sendMessage(headers, chat.getId());
            MessageDto message = responseEntity.getBody();
            if(textArea.getText().equals(""))
                textArea.appendText(message.toString());
            else
                textArea.appendText("\n" + message.toString());

            textField.clear();
            textField.requestFocus();
        }
    }

    @FXML
    private void sendEnter(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            sendClick();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textField.requestFocus();
        textArea.setWrapText(true);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("token", CurrentUser.getInstance().getToken());
        ResponseEntity<ChatDto> responseEntity = chatService.getChat(headers, CurrentUser.getInstance().getIdChat());
        chat = responseEntity.getBody();
        chatName.setText(chat.getName());
        ResponseEntity<List<MessageDto>> responseEntity2 = messageService.getAllMessages(headers, chat.getId());
        List<MessageDto> messages = responseEntity2.getBody();
        for(MessageDto message: messages) {
            if(textArea.getText().equals(""))
                textArea.appendText(message.toString());
            else
                textArea.appendText("\n" + message.toString());
        }
    }
}
