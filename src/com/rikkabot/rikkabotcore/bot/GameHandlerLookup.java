package com.rikkabot.rikkabotcore.bot;

import java.util.HashMap;
import java.util.Map;

import com.rikkabot.rikkabotcore.bot.commands.Command;

import com.manulaiko.tabitha.Console;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Game handler lookup.
 * ====================
 *
 * Finds the handler of a specified command.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class GameHandlerLookup {
    /**
     * Handlers for the available commands.
     */
    private Map<Short, Class> handlers = new HashMap<>();

    /**
     * Finds and returns a handler for the given command.
     *
     * @param connection Connection that received the command.
     * @param command    Received command.
     *
     * @return Handler for `command`.
     */
    public Handler handler(GameConnection connection, Command command) {
        Class handlerClass = this.handlers().get(command.id());

        if (handlerClass == null) {
            return null;
        }

        Object handler = null;
        try {
            handler = handlerClass.getConstructor(GameConnection.class, Command.class)
                                  .newInstance(connection, command);
        } catch (Exception e) {
            Console.print(e);
        }

        return (Handler)handler;
    }
}
