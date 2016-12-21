package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
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

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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

    private SceneManager sceneManager = SceneManager.getInstance();

    private ChatService chatService = ChatService.getInstance();

    @FXML
    public void createChatClick(ActionEvent event) {

        if(nameLabel.isVisible() && nameField.isVisible() && create.isVisible()) {

            pane.setEffect(new GaussianBlur(0));
            nameLabel.setVisible(false);
            nameField.setVisible(false);
            create.setVisible(false);

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
    public void logoutClick(ActionEvent event) {
        CurrentUser.getInstance().clear();
        try (FileOutputStream fileOutputStream = new FileOutputStream("Service/src/main/resources/CurrentUser.ser")) {
            fileOutputStream.write(("").getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        sceneManager.showLoginScene();
    }

    @FXML
    public void createClick(ActionEvent event) {
        if(nameField.getText().isEmpty()) {
            nameField.setUnFocusColor(Paint.valueOf("#ff0000"));
            nameField.setFocusColor(Paint.valueOf("#ff7d7f"));
            return;
        }
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("token", CurrentUser.getInstance().getToken());
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", nameField.getText());
        ResponseEntity<ChatDto> responseEntity = chatService.postCreateChat(headers, parameters);
        if(responseEntity.getStatusCode().is2xxSuccessful())
            sceneManager.showChatScene(nameField.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        username.setText(CurrentUser.getInstance().toString());
    }
}
