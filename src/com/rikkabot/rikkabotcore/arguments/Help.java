package com.rikkabot.rikkabotcore.arguments;

import com.rikkabot.rikkabotcore.Main;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

/**
 * Help argument.
 * ==============
 *
 * Displays the help page and exits.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Help extends Argument {
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
                arg.equalsIgnoreCase("-h") ||
                arg.equalsIgnoreCase("--help")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle() {
        Console.println(
                "RikkaBot v"+ Main.version +"\n" +
                "\n" +
                "The best open-source DarkOrbit packet bot.\n" +
                "\n" +
                "Usage:\n" +
                "    java -jar RikkaBot.jar ([arguments])\n" +
                "\n" +
                "Available arguments:\n" +
                "    -h      --help         Prints this page and exists.\n" +
                "    -d      --debug        Enables debug mode (default = false).\n" +
                "    -s=bool --showGUI=bool Sets whether the GUI should be rendered or not (default = true).\n" +
                "\n" +
                "Examples:\n" +
                "    java -jar RikkaBot.jar -d\n" +
                "    java -jar RikkaBot.jar --showGUI=false\n" +
                "\n" +
                "Sources are available in GitHub: https://github.com/RikkaBot/RikkaBot-core\n" +
                "For support and updates join our discord server: https://discord.gg/HP68ECx"
        );

        System.exit(0);
    }
}
