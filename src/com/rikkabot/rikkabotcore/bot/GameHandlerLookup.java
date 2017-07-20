package com.rikkabot.rikkabotcore.bot;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.bot.commands.incoming.HandshakeCommand;
import com.rikkabot.rikkabotcore.bot.commands.incoming.VersionCommand;
import com.rikkabot.rikkabotcore.bot.handlers.HandshakeCommandHandler;
import com.rikkabot.rikkabotcore.bot.handlers.VersionCommandHandler;
import com.rikkabot.rikkabotcore.utils.HandlerLookup;

/**
 * Game handler lookup.
 * ====================
 *
 * Finds the handler of a specified command.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class GameHandlerLookup extends HandlerLookup{
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
     * Constructor.
     *
     * Sets available handlers.
     */
    public Map<Short, Class> getHandlers() {
        Map<Short, Class> handlers = new HashMap<>();

        handlers.put(VersionCommand.ID, VersionCommandHandler.class);
        handlers.put(HandshakeCommand.ID, HandshakeCommandHandler.class);

        return handlers;
    }
}
