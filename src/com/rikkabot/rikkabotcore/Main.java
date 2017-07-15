package com.rikkabot.rikkabotcore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.rikkabot.rikkabotcore.arguments.Debug;
import com.rikkabot.rikkabotcore.arguments.Help;
import com.rikkabot.rikkabotcore.arguments.ShowGUI;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.ArgumentParser;
import com.manulaiko.tabitha.utils.CommandPrompt;

/**
 * Main application class.
 * =======================
 *
 * Entry point of the application.
 */
public class Main extends Application {
    /**
     * Application version.
     */
    public static final String version = "0.0.0";

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
        Main.parseArguments(args);

        Console.println("RikkaBot v"+ Main.version);

        if (Settings.showGUI) {
            Console.debug("Starting GUI...");

            Main.launch(args);

            return;
        }

        Console.debug("Starting command prompt...");

        CommandPrompt cp = new CommandPrompt();

        cp.start();
    }

    /**
     * Parses command line arguments.
     *
     * @param args Command line arguments.
     */
    private static void parseArguments(String[] args) {
        ArgumentParser ap = new ArgumentParser(args);

        ap.add(new Debug());
        ap.add(new ShowGUI());
        ap.add(new Help());

        ap.parse();
    }
}
