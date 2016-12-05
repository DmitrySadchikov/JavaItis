package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.itis.chat.gui.scenes.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private Stage stage;

    @FXML
    private JFXTextField login;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton signIn;
    @FXML
    private JFXButton signUp;
    @FXML
    private Text error;

    private SceneManager sceneManager = SceneManager.getInstance();


    @FXML
    void singInClick(ActionEvent event) {
        sceneManager.showProfileScene("Username");
    }

    @FXML
    void signUpClick(ActionEvent event) {
        sceneManager.showRegistrationScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
