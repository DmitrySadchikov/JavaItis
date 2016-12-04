package ru.itis.chat.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

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
