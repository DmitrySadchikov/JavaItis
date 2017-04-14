package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.dto.ChatDto;
import ru.itis.chat.gui.scenes.SceneManager;
import ru.itis.chat.models.CurrentUser;
import ru.itis.chat.services.ChatService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController implements Initializable{

    @FXML
    private JFXButton createChat;
    @FXML
    private JFXButton logout;
    @FXML
    private Text username;
    @FXML
    private JFXButton create;
    @FXML
    private JFXTextField nameField;
    @FXML
    private Label nameLabel;
    @FXML
    private Pane pane;
    @FXML
    private GridPane gridPane;

    private SceneManager sceneManager = SceneManager.getInstance();

    private ChatService chatService = ChatService.getInstance();

    @FXML
    public void createChatClick() {

        if(nameLabel.isVisible() && nameField.isVisible() && create.isVisible()) {

            pane.setEffect(new GaussianBlur(0));
            nameLabel.setVisible(false);
            nameField.setVisible(false);
            create.setVisible(false);

            gridPane.setDisable(false);

            nameField.setUnFocusColor(Paint.valueOf("#009bff"));
            nameField.setFocusColor(Paint.valueOf("#52bcff"));

            createChat.setStyle("-fx-background-color: #009bff;");
            createChat.setTextFill(Paint.valueOf("#ffffff"));
            createChat.setRipplerFill(Paint.valueOf("#d4efff"));
            createChat.setText("Create chat");
        }
        else {
            pane.setEffect(new GaussianBlur(7));
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            create.setVisible(true);
            createChat.setStyle("-fx-background-color: transparent;");
            createChat.setTextFill(Paint.valueOf("#009bff"));
            createChat.setRipplerFill(Paint.valueOf("#83d2ff"));
            createChat.setText("Cancel");
        }
    }

    @FXML
    public void logoutClick() {
        CurrentUser.getInstance().clear();
        sceneManager.showLoginScene();
    }

    @FXML
    public void createClick() {
        if(nameField.getText().isEmpty()) {
            nameField.setUnFocusColor(Paint.valueOf("#ff0000"));
            nameField.setFocusColor(Paint.valueOf("#ff7d7f"));
            return;
        }
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("token", CurrentUser.getInstance().getToken());
        //headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("name", nameField.getText());
        ResponseEntity<ChatDto> responseEntity = chatService.postCreateChat(headers);
        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            CurrentUser.getInstance().setIdChat(responseEntity.getBody().getId());
            sceneManager.showChatScene(nameField.getText());
        }
    }

    private EventHandler<ActionEvent> getOnActionEventHandler(Long chatId) {
        return event -> {
            CurrentUser.getInstance().setIdChat(chatId);
            JFXButton button = (JFXButton) event.getSource();
            sceneManager.showChatScene(button.getText());
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        username.setText(CurrentUser.getInstance().toString());
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("token", CurrentUser.getInstance().getToken());
        ResponseEntity<List<ChatDto>> response =  chatService.getChats(headers);
        if(response.getStatusCode().is2xxSuccessful()) {
            List<ChatDto> chats = response.getBody();
            int i = 0;
            for(ChatDto chat : chats) {
                //CurrentUser.getInstance().addChat(chat.getId(), chat.getName());
                JFXButton jfxButton = new JFXButton(chat.getName());
                jfxButton.setRipplerFill(Paint.valueOf("#83d2ff"));
                jfxButton.setPrefSize(1000, 50);
                jfxButton.setAlignment(Pos.CENTER_LEFT);
                jfxButton.setOnAction(getOnActionEventHandler(chat.getId()));
                //jfxButton.setTextFill(Paint.valueOf("#009bff"));
                gridPane.add(jfxButton, 0, i);
                i++;
            }
        }
    }
}
