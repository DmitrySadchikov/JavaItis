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

    private void showScene(String url, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(url));
            stage.setTitle(title);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
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
