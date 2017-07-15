package com.rikkabot.rikkabotcore.bot.handlers;

import com.rikkabot.rikkabotcore.bot.GameConnection;
import com.rikkabot.rikkabotcore.bot.commands.Command;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Handler class.
 * =============
 *
 * All command handlers must extend this class.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public abstract class Handler<T extends Command>
{
    /**
     * Connection that received the command.
     */
    private GameConnection connection;

    /**
     * Received command.
     */
    private T command;

    /**
     * Constructor.
     *
     * @param connection Connection that received the command.
     * @param command    Received command.
     */
    public Handler(GameConnection connection, Command command)
    {
        this.connection(connection)
            .command((T)command);
    }

    /**
     * Handles the command.
     */
    public abstract void handle();
}
