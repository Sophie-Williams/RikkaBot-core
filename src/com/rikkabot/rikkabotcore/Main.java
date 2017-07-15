package com.rikkabot.rikkabotcore;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.Policy;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.ArgumentParser;
import com.manulaiko.tabitha.utils.CommandPrompt;

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

        cp.start();
    }

    /**
     * Starts the GUI from specified jar in settings.
     */
    private static void startGUI() {
        Policy.setPolicy(new PluginPolicy());
        System.setSecurityManager(new SecurityManager());
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
