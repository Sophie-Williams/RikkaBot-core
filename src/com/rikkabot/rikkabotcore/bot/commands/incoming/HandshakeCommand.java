package com.rikkabot.rikkabotcore.bot.commands.incoming;

import com.rikkabot.rikkabotcore.bot.commands.Command;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by piotr on 14.07.17.
 */
public class HandshakeCommand extends Command {

    /**
     * Command ID.
     */
    public static final short ID = 26074;

    /**
     * Key of the server
     */
    public byte[] code;

    public HandshakeCommand(byte[] code) {
        super(HandshakeCommand.ID);

        this.code = code;
    }

    public HandshakeCommand() {
        super(HandshakeCommand.ID);
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
        output.writeInt(code.length);
        output.write(code, 0, code.length);
        output.writeShort(5458);
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
        int len = input.readInt();
        this.code = new byte[len];
        input.readFully(this.code, 0, len);
        input.readShort();
    }
}
