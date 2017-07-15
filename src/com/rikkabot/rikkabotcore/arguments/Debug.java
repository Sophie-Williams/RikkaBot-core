package com.rikkabot.rikkabotcore.arguments;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Debug arguments.
 * ================
 *
 * Sets the application running in debug mode.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Debug extends Argument {
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
                arg.equalsIgnoreCase("-d") ||
                arg.equalsIgnoreCase("--debug")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle() {
        Console.debug = true;

        Console.debug("Running in debug mode!");
    }
}
