package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ru.itis.chat.gui.scenes.SceneManager;

import java.net.URL;
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

    private SceneManager sceneManager = SceneManager.getInstance();

    @FXML
    void backClick(ActionEvent event) {
        sceneManager.showProfileScene("Username");
    }

    @FXML
    void sendClick(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
