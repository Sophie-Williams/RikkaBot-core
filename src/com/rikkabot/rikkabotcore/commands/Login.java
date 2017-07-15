package com.rikkabot.rikkabotcore.commands;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.ICommand;

import com.rikkabot.rikkabotcore.dao.FactoryManager;
import com.rikkabot.rikkabotcore.dao.hero.Hero;

/**
 * Login command.
 * ==============
 *
 * Command to login through the CLI.
 *
 * Usage:
 *    login username password
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Login implements ICommand {
    /**
     * Executes the command.
     *
     * @param command Command arguments.
     */
    @Override
    public void execute(String[] command) {
        if (command.length != 3) {
            Console.println(this.getDescription());

            return;
        }

        Console.debug("Logging in...");

        Hero hero = FactoryManager.heroes.login(command[1], command[2]);

        Console.debug("Successfully logged in!");
        Console.debug("Connecting...");

        hero.connect();
    }

    /**
     * Checks whether this command can execute `name` command.
     *
     * @param name Command name to check.
     * @return Whether this command can execute `name`.
     */
    @Override
    public boolean canExecute(String name) {
        return name.equalsIgnoreCase("login");
    }

    /**
     * Returns command name.
     *
     * @return Command name.
     */
    @Override
    public String getName() {
        return "login";
    }

    /**
     * Returns command description.
     *
     * @return Command description.
     */
    @Override
    public String getDescription() {
        return "Login to your account.\n" +
               "Usage:\n" +
               "    login (username) (password)";
    }
}
