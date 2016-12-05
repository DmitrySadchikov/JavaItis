package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.gui.scenes.SceneManager;
import ru.itis.chat.models.CurrentUser;
import ru.itis.chat.services.RegistrationService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private RegistrationService registrationService = RegistrationService.getInstance();

    private SceneManager sceneManager = SceneManager.getInstance();

    @FXML
    public void backClick(ActionEvent event) {
        sceneManager.showLoginScene();
    }

    @FXML
    public void signUpClick(ActionEvent event) {

        if(!login.getText().equals("") && !checkLogin()) {
            login.setUnFocusColor(Paint.valueOf("#ff0000"));
            login.setFocusColor(Paint.valueOf("#ff7d7f"));
            error.setText("Login must contain at least three symbols, only Latin letters and symbols \".\" \"-\", \"_\"");
            return;
        }
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

    private boolean checkLogin() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
        Matcher matcher = pattern.matcher(login.getText());
        return matcher.matches();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
