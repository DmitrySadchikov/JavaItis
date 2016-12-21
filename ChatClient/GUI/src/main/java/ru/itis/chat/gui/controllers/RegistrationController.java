package ru.itis.chat.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import ru.itis.chat.gui.scenes.SceneManager;
import ru.itis.chat.services.AuthorizationService;

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
    @FXML
    private JFXSpinner spinner;
    @FXML
    private Pane pane;

    private AuthorizationService authorizationService = AuthorizationService.getInstance();

    private SceneManager sceneManager = SceneManager.getInstance();

    @FXML
    public void backClick() {
        sceneManager.showLoginScene();
    }

    @FXML
    public void signUpClick() {

        if(login.getText().equals("") || password.getText().equals("")
                || firstName.getText().equals("") || lastName.getText().equals("")) {
            error.setText("All fields must be filled");
            return;
        }

        if(!checkLengthLogin()) {
            error.setText("Login must contain at least three symbols");
            return;
        }

        //TODO: не работает

        Thread requestThread = new Thread(() -> {
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("login", login.getText());
            headers.add("password", password.getText());
            headers.add("last_name", lastName.getText());
            headers.add("first_name", firstName.getText());

            authorizationService.postAuthorization("/registration", headers);

        });

        startSpinner();
        requestThread.start();

        try {
            requestThread.join();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        }

        if(authorizationService.httpStatusCodeIs2xxSuccessful()) {
            sceneManager.showProfileScene();
            return;
        }

        stopSpinner();
        if(authorizationService.httpStatusCodeIs4xxClientError())
            error.setText("This login already exists");
        if(authorizationService.httpStatusCodeIs5xxServerError())
            error.setText("Internal server error");

    }

    private void startSpinner() {
        signUp.setDisable(true);
        pane.setEffect(new GaussianBlur(7));
        spinner.setVisible(true);
        login.setDisable(true);
        password.setDisable(true);
        confirm.setDisable(true);
        lastName.setDisable(true);
        firstName.setDisable(true);
        back.setDisable(true);
    }

    private void stopSpinner() {
        pane.setEffect(new GaussianBlur(0));
        spinner.setVisible(false);
        login.setDisable(false);
        password.setDisable(false);
        confirm.setDisable(false);
        lastName.setDisable(false);
        firstName.setDisable(false);
        back.setDisable(false);
        signUp.setDisable(false);
    }

    private boolean check() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]*$");
        Matcher matcher = pattern.matcher(login.getText());
        return matcher.matches();
    }

    private boolean checkLengthLogin() {
        Pattern pattern = Pattern.compile("^.{3,}$");
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

    @FXML
    private void checkConfirm() {
        if(confirm.getText().equals("")) {
            confirm.setUnFocusColor(Paint.valueOf("#009bff"));
            confirm.setFocusColor(Paint.valueOf("#52bcff"));
            error.setText("");
            return;
        }
        if(password.getText().length() == confirm.getText().length()) {
            if(!password.getText().equals(confirm.getText())) {
                confirm.setUnFocusColor(Paint.valueOf("#ff0000"));
                confirm.setFocusColor(Paint.valueOf("#ff7d7f"));
                error.setText("Passwords do not match");
                return;
            }
            else {
                confirm.setUnFocusColor(Paint.valueOf("#00cc40"));
                confirm.setFocusColor(Paint.valueOf("#30d058"));
                error.setText("");
                return;
            }
        }
        if(password.getText().length() < confirm.getText().length()) {
            confirm.setUnFocusColor(Paint.valueOf("#ff0000"));
            confirm.setFocusColor(Paint.valueOf("#ff7d7f"));
            error.setText("Passwords do not match");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
