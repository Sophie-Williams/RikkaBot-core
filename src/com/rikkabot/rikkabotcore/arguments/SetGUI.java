package com.rikkabot.rikkabotcore.arguments;

import java.io.File;

import com.rikkabot.rikkabotcore.Settings;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Set GUI argument.
 * =================
 *
 * Sets which GUI to display
 *
 * Example:
 *
 * ```
 * java -jar RikkaBot.jar --setGUI=gui/default.jar
 * ```
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class SetGUI extends Argument {
    /**
     * Checks that the argument can be handled
     * by this instance.
     *
     * @param arg Argument.
     * @return `true` if the argument can be handled by this instance, `false` if not.
     */
    @Override
    public boolean canHandle(String arg) {
        return (
                arg.equalsIgnoreCase("-s") ||
                arg.equalsIgnoreCase("--setGUI")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle() {
        if(!this.valueIsValid()) {
            this.printUsage();
            Settings.showGUI(false);

            return;
        }

        File gui = new File(super.value());

        Settings.showGUI(gui.isFile());
        Settings.gui(gui);

        if (!Settings.showGUI()) {
            Console.debug("Running in CLI mode!");
        }
    }

    /**
     * Checks that the argument value is valid or not.
     */
    private boolean valueIsValid() {
        String value = super.value();

        return !value.isEmpty() && value.endsWith(".jar");
    }

    /**
     * Prints argument usage.
     */
    private void printUsage() {
        Console.println("Usage:");
        Console.println("    java -jar RikkaBot.jar --setGUI=gui/default.jar");
        Console.println("Defaulting to CLI mode!");
    }
}
