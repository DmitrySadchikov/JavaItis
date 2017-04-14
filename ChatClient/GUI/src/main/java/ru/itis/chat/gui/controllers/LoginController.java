package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.gui.scenes.SceneManager;
import ru.itis.chat.services.AuthorizationService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void checkLogin() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]*$");
        Matcher matcher = pattern.matcher(login.getText());
        if(!login.getText().equals("") && !matcher.matches()) {
            login.setUnFocusColor(Paint.valueOf("#ff0000"));
            login.setFocusColor(Paint.valueOf("#ff7d7f"));
            error.setText("Incorrect symbols");
        }
        else {
            login.setUnFocusColor(Paint.valueOf("#009bff"));
            login.setFocusColor(Paint.valueOf("#52bcff"));
            error.setText("");
        }
    }

    @FXML
    public void checkPassword() {
        if(password.getText().equals("")) {
            error.setText("");
        }
    }

    @FXML
    public void singInClick() {
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
    public void signUpClick() {
        sceneManager.showRegistrationScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
