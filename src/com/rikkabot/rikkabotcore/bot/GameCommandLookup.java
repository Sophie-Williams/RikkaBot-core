package com.rikkabot.rikkabotcore.bot;

import java.util.HashMap;
import java.util.Map;

import com.rikkabot.rikkabotcore.bot.commands.incoming.HandshakeCommand;
import com.rikkabot.rikkabotcore.bot.commands.incoming.ObfuscationCommand;
import com.rikkabot.rikkabotcore.bot.commands.incoming.VersionCommand;

import lombok.Data;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.utils.CommandLookup;

/**
 * Game command lookup.
 * ====================
 *
 * Lookup for game commands
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class GameCommandLookup extends CommandLookup {
    /**
     * Constructor.
     *
     * @param connection Game connection;
     */
    public GameCommandLookup(GameConnection connection) {
        super(GameHandlerLookup.instance(), connection);
    }

    /**
     * Builds and returns the map with available commands.
     *
     * @return Map with available commands.
     */
    @Override
    public Map<Short, Class> getCommands() {
        Map<Short, Class> commands = new HashMap<>();

        commands.put(VersionCommand.ID, VersionCommand.class);
        commands.put(HandshakeCommand.ID, HandshakeCommand.class);
        commands.put(ObfuscationCommand.ID, ObfuscationCommand.class);

        return commands;
    }
}
