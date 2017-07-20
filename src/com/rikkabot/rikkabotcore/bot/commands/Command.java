package com.rikkabot.rikkabotcore.bot.commands;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Basic command.
 * ==============
 *
 * Basic command received from a socket server.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public abstract class Command extends com.rikkabot.rikkabotcore.utils.Command
{
    /**
     * Constructor.
     *
     * @param id Command ID.
     */
    public Command(short id) {
        super(id);
    }

    /**
     * Checks that the packet needs encryption or not.
     *
     * @return Whether this command needs encryption or not.
     */
    public boolean needsEncryption() {
        return true;
    }
}
