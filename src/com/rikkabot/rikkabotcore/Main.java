package com.rikkabot.rikkabotcore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main application class.
 * =======================
 *
 * Entry point of the application.
 */
public class Main extends Application {
    /**
     * Loads and starts the GUI.
     *
     * @param primaryStage Application GUI.
     *
     * @throws Exception If something goes wrong.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("RikkaBot - 0 accounts running");
        primaryStage.setScene(new Scene(root, 600, 349));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        primaryStage.show();
    }

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Main.launch(args);
    }
}
