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
        Main.exit(
                "RikkaBot v"+ Main.version,
                "",
                "The best open-source DarkOrbit packet bot.",
                "",
                "Usage:",
                "    java -jar RikkaBot.jar ([arguments])",
                "",
                "Available arguments:",
                "    -h      --help         Prints this page and exists.",
                "    -d      --debug        Enables debug mode (default = false).",
                "    -s=gui  --setGUI=gui   Sets the gui to load (default = gui/default.jar).",
                "",
                "Examples:",
                "    java -jar RikkaBot.jar -d",
                "    java -jar RikkaBot.jar --showGUI=false",
                "",
                "Sources are available in GitHub: https://github.com/RikkaBot/RikkaBot-core",
                "For support and updates join our discord server: https://discord.gg/HP68ECx"
        );
    }
}
