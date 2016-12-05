package ru.itis.chat.gui.application;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.itis.chat.gui.scenes.SceneManager;

public class StartUp extends Application {

    private SceneManager sceneManager = SceneManager.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        sceneManager.setStage(primaryStage);
        sceneManager.showLoginScene();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
