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

    @FXML
    private Text validation;

    private RegistrationService registrationService = RegistrationService.getInstance();

    private SceneManager sceneManager = SceneManager.getInstance();

    @FXML
    public void backClick(ActionEvent event) {
        sceneManager.showLoginScene();
    }

    @FXML
    public void signUpClick(ActionEvent event) {

        if(!checkLengthLogin()) {
            error.setText("Login must contain at least four symbols");
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

    private boolean check() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]*$");
        Matcher matcher = pattern.matcher(login.getText());
        return matcher.matches();
    }

    private boolean checkLengthLogin() {
        Pattern pattern = Pattern.compile("^.{4,}$");
        Matcher matcher = pattern.matcher(login.getText());
        return matcher.matches();
    }

    @FXML
    private void checkLogin() {
        if(!login.getText().equals("") && !check()) {
            login.setUnFocusColor(Paint.valueOf("#ff0000"));
            login.setFocusColor(Paint.valueOf("#ff7d7f"));
            error.setText("Use Latin letters and symbols \n \".\" \"-\", \"_\"");
        }
        else {
            login.setUnFocusColor(Paint.valueOf("#009bff"));
            login.setFocusColor(Paint.valueOf("#52bcff"));
            error.setText("");
        }
    }

    @FXML
    private void checkPassword() {
        if(!password.getText().equals(""))
            validation.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
