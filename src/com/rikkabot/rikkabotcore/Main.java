package com.rikkabot.rikkabotcore;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.ArgumentParser;
import com.manulaiko.tabitha.utils.CommandPrompt;

import com.rikkabot.rikkabotcore.commands.Login;
import com.rikkabot.rikkabotcore.plugin.API;
import com.rikkabot.rikkabotcore.plugin.gui.GUIManager;
import com.rikkabot.rikkabotcore.arguments.Debug;
import com.rikkabot.rikkabotcore.arguments.Help;
import com.rikkabot.rikkabotcore.arguments.SetGUI;

/**
 * Main application class.
 * =======================
 *
 * Entry point of the application.
 */
public class Main {
    /**
     * Application version.
     */
    public static final String version = "0.0.0";

    /**
     * API instance.
     */
    public static final API api = new API() {};

    /**
     * GUI Manager.
     */
    public static final GUIManager guiManager = new GUIManager();

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Main.parseArguments(args);

        Console.println("RikkaBot v"+ Main.version);

        if (Settings.showGUI()) {
            Main.startGUI();

            return;
        }

        Main.startCommandPrompt();
    }

    /**
     * Parses command line arguments.
     *
     * @param args Command line arguments.
     */
    private static void parseArguments(String[] args) {
        ArgumentParser ap = new ArgumentParser(args);

        ap.add(new Debug());
        ap.add(new SetGUI());
        ap.add(new Help());

        ap.parse();
    }

    /**
     * Starts the command prompt.
     */
    private static void startCommandPrompt() {
        Console.debug("Starting command prompt...");

        CommandPrompt cp = new CommandPrompt();

        cp.addCommand(new Login());

        cp.start();
    }

    /**
     * Starts the GUI from specified jar in settings.
     */
    private static void startGUI() {
        if(!Main.guiManager.start(Settings.gui())) {
            Console.println("Can't start GUI! Defaulting to CLI...");

            Main.startCommandPrompt();
        }
    }

    /**
     * Finishes execution.
     *
     * @param code     Exit code.
     * @param messages Messages to print before exiting.
     */
    public static void exit(int code, String... messages) {
        for(String message : messages) {
            Console.println(message);
        }

        System.exit(code);
    }

    /**
     * Finishes execution.
     *
     * @param messages Messages to print before exiting.
     */
    public static void exit(String... messages) {
        Main.exit(0, messages);
    }
}
