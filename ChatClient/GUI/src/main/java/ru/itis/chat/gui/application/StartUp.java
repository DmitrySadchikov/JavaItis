package ru.itis.chat.gui.application;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.itis.chat.gui.scenes.SceneManager;
import ru.itis.chat.models.CurrentUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class StartUp extends Application {

    private SceneManager sceneManager = SceneManager.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneManager.setStage(primaryStage);

        File out = new File("Service/src/main/resources/CurrentUser.ser");
        if(out.length() != 0) {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(out));
            CurrentUser currentUser = (CurrentUser) inputStream.readObject();
            inputStream.close();
            sceneManager.showProfileScene();
        }
        else
            sceneManager.showLoginScene();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
