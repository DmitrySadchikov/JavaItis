package ru.itis.chat.gui.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.itis.chat.models.CurrentUser;

import java.io.IOException;

public class SceneManager {

    private static final String LOGIN = "/fxml/login.fxml";
    private static final String REGISTRATION = "/fxml/registration.fxml";
    private static final String PROFILE = "/fxml/profile.fxml";
    private static final String CHAT = "/fxml/chat.fxml";

    private Stage stage;

    private static SceneManager instance = new SceneManager();

    public static SceneManager getInstance() {
        return instance;
    }

    private SceneManager() {}

    private void showScene(Stage primaryStage, String url, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(url));
            primaryStage.setTitle(title);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void showScene(String url, String title){
        showScene(this.stage, url, title);
    }

    public void showLoginScene() {
        showScene(LOGIN, "Welcome");
    }

    public void showRegistrationScene() {
        showScene(REGISTRATION, "Registration");
    }

    public void showProfileScene() {
        showScene(PROFILE, CurrentUser.getInstance().toString());
    }

    public void showChatScene(String title) {showScene(CHAT, title);}

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
