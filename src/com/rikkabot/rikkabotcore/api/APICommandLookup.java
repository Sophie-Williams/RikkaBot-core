package com.rikkabot.rikkabotcore.api;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.utils.CommandLookup;

/**
 * API command lookup.
 * ====================
 *
 * Lookup for API commands
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class APICommandLookup extends CommandLookup {
    /**
     * API connection instance.
     */
    private APIConnection connection;

    /**
     * Constructor.
     *
     * @param connection Game connection;
     */
    public APICommandLookup(APIConnection connection) {
        super(null, connection);
    }

    /**
     * Builds and returns the map with available commands.
     *
     * @return Map with available commands.
     */
    @Override
    public Map<Short, Class> getCommands() {
        HashMap<Short, Class> commands = new HashMap<>();

        return commands;
    }
}
