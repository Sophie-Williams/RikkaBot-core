package com.rikkabot.rikkabotcore.bot.commands;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import lombok.AllArgsConstructor;
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
@Accessors @Data @AllArgsConstructor
public abstract class Command
{
    /**
     * Command ID.
     */
    private Short id;

    /**
     * Writes the command to the output stream.
     *
     * @param output Output stream to write the command to.
     *
     * @throws IOException In case the write failed.
     */
    public void write(DataOutput output) throws IOException
    {
        output.writeShort(this.id());
        this._write(output);
    }

    /**
     * Performs the specific write for the command.
     *
     * @param output Output stream to write the command to.
     *
     * @throws IOException In case the write failed.
     */
    protected abstract void _write(DataOutput output) throws IOException;

    /**
     * Reads the command from the input stream.
     *
     * @param input Input stream to read the command from.
     *
     * @throws IOException In case the read failed.
     */
    public abstract void read(DataInput input) throws IOException;

    /**
     * Checks that the packet needs encryption or not.
     *
     * @return Whether this command needs encryption or not.
     */
    public boolean needsEncryption() {
        return true;
    }
}
