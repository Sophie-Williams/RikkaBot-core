package com.rikkabot.rikkabotcore.bot.commands.outgoing;

import com.rikkabot.rikkabotcore.bot.commands.Command;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by piotr on 14.07.17.
 */
public class ObfuscationRequest extends Command {

    /**
     * Command ID.
     */
    public static final short ID = 5261;

    public ObfuscationRequest() {
        super(ObfuscationRequest.ID);
    }

    /**
     * Writes the command to the output stream.
     *
     * @param output Output stream to write the command to.
     *
     * @throws IOException In case the write failed.
     */
    @Override
    protected void _write(DataOutput output) throws IOException {
        output.writeShort(-8514);
    }

    /**
     * Reads the command from the input stream.
     *
     * @param input Input stream to read the command from.
     *
     * @throws IOException In case the read failed.
     */
    @Override
    public void read(DataInput input) throws IOException {
        input.readShort();
    }
}
