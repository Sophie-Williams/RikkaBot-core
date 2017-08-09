package com.rikkabot.rikkabotcore.commands;

import org.json.JSONArray;
import org.json.JSONObject;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.ICommand;

import com.rikkabot.rikkabotcore.Main;
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

        Console.println("Logging in...");

        String[] args = new String[2];
        args[0] = command[1];
        args[1] = command[2];

        JSONObject response = Main.endpoint.find("login").execute(new JSONArray(args));

        response.getJSONArray("messages").forEach(Console::println); // so sweet

        if(response.getBoolean("isError")) {
            return;
        }

        Console.debug("Connecting...");
        Hero hero = FactoryManager.heroes.fromResponse(response);

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
