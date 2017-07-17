package com.rikkabot.rikkabotcore.bot;

import java.util.HashMap;
import java.util.Map;

import com.rikkabot.rikkabotcore.bot.commands.incoming.HandshakeCommand;
import com.rikkabot.rikkabotcore.bot.commands.incoming.VersionCommand;
import com.rikkabot.rikkabotcore.bot.handlers.HandshakeCommandHandler;
import com.rikkabot.rikkabotcore.bot.handlers.VersionCommandHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.bot.commands.Command;
import com.rikkabot.rikkabotcore.bot.handlers.Handler;

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
    ///////////////////////////////////
    // Static methods and properties //
    ///////////////////////////////////
    /**
     * Singleton instance.
     */
    private static GameHandlerLookup instance;

    /**
     * Returns the singleton instance.
     *
     * @return Singleton instance.
     */
    public static GameHandlerLookup instance()
    {
        if(GameHandlerLookup.instance == null) {
            GameHandlerLookup.instance = new GameHandlerLookup();
        }

        return GameHandlerLookup.instance;
    }

    ///////////////////////////////////////
    // Non static methods and properties //
    ///////////////////////////////////////
    /**
     * Handlers for the available commands.
     */
    private Map<Short, Class> handlers = new HashMap<>();

    /**
     * Constructor.
     *
     * Sets available handlers.
     */
    public GameHandlerLookup() {
        // TODO populate `this.handlers`: `this.handlers().put(Command.ID, Handler.class);`
        this.handlers().put(VersionCommand.ID, VersionCommandHandler.class);
        this.handlers().put(HandshakeCommand.ID, HandshakeCommandHandler.class);
    }

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
