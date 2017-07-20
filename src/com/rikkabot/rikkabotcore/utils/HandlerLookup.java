package com.rikkabot.rikkabotcore.utils;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.Console;

/**
 * Generic handler lookup.
 * =======================
 *
 * Finds the handler of a specified command.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public abstract class HandlerLookup {
    /**
     * Handlers for the available commands.
     */
    private Map<Short, Class> handlers = new HashMap<>();

    /**
     * Constructor.
     *
     * Sets available handlers.
     */
    public HandlerLookup() {
        this.handlers(this.getHandlers());
    }

    /**
     * Finds and returns a handler for the given command.
     *
     * @param connection Connection that received the command.
     * @param command    Received command.
     *
     * @return Handler for `command`.
     */
    public Handler handler(Connection connection, Command command) {
        Class handlerClass = this.handlers().get(command.id());

        if (handlerClass == null) {
            return null;
        }

        Object handler = null;
        try {
            handler = handlerClass.getConstructor(Connection.class, Command.class)
                                  .newInstance(connection, command);
        } catch (Exception e) {
            Console.print(e);
        }

        return (Handler)handler;
    }


    /**
     * Builds and returns the map with available handlers.
     *
     * @return Map with available handlers.
     */
    public abstract Map<Short, Class> getHandlers();
}
