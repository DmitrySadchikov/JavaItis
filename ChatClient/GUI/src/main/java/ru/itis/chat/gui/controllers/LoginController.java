package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.gui.scenes.SceneManager;
import ru.itis.chat.services.AuthorizationService;

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

    private AuthorizationService authorizationService = AuthorizationService.getInstance();

    @FXML
    void singInClick(ActionEvent event) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("login", login.getText());
        headers.add("password", password.getText());

        authorizationService.postAuthorization("/login", headers);

        if(authorizationService.httpStatusCodeIs2xxSuccessful())
            sceneManager.showProfileScene();
        if(authorizationService.httpStatusCodeIs4xxClientError())
            error.setText("Incorrect login or password");
    }

    @FXML
    void signUpClick(ActionEvent event) {
        sceneManager.showRegistrationScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
