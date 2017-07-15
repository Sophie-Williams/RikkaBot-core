package com.rikkabot.rikkabotcore.arguments;

import com.rikkabot.rikkabotcore.Settings;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Show GUI argument.
 * ==================
 *
 * Sets whether the GUI should be rendered or not.
 *
 * Example:
 *
 * ```
 * java -jar rikkaBot.jar --showGUI=false
 * ```
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ShowGUI extends Argument {
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
                arg.equalsIgnoreCase("--showGUI")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle() {
        String value = super.value();
        if (value.isEmpty()) {
            this.printUsage();

            return;
        }

        boolean show;
        try {
            show = Boolean.parseBoolean(value);
        } catch (Exception e) {
            if (Settings.debug) {
                Console.print(e);
            }

            this.printUsage();

            return;
        }

        Settings.showGUI = show;

        if (!show) {
            Console.debug("Running in CLI mode!");
        }
    }

    /**
     * Prints argument usage.
     */
    private void printUsage() {
        Console.println("Usage:");
        Console.println("    java -jar rikkaBot.jar --showGUI=false");
        Console.println("Defaulting to `true`!");
    }
}
