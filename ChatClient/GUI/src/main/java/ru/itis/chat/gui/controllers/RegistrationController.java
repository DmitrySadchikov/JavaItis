package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.gui.SceneManager;
import ru.itis.chat.models.CurrentUser;
import ru.itis.chat.services.interfaces.RegistrationService;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {


    @FXML
    private JFXButton signUp;

    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirm;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXButton back;

    @FXML
    private Text error;

    @Autowired
    RegistrationService registrationService;

    private SceneManager sceneManager = SceneManager.getInstance();

    @FXML
    public void backClick(ActionEvent event) {
        sceneManager.showLoginScene();
    }

    @FXML
    public void signUpClick(ActionEvent event) {
        if(login.getText().equals("") || password.getText().equals("")
                || firstName.getText().equals("") || lastName.getText().equals("")) {
            error.setText("All fields must be filled");
            return;
        }
        if(!password.getText().equals(confirm.getText())) {
            confirm.setUnFocusColor(Paint.valueOf("#ff0000"));
            confirm.setFocusColor(Paint.valueOf("#ff7d7f"));
            error.setText("Passwords do not match");
            return;
        }

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("login", login.getText());
        headers.add("password", password.getText());
        headers.add("last_name", lastName.getText());
        headers.add("first_name", firstName.getText());

        registrationService.postRegistration(headers);

        if(registrationService.httpStatusCodeIs2xxSuccessful())
            sceneManager.showProfileScene(CurrentUser.getInstance().toString());
        if(registrationService.httpStatusCodeIs4xxClientError())
            error.setText("This login already exists");
        if(registrationService.httpStatusCodeIs5xxServerError())
            error.setText("Internal server error");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
