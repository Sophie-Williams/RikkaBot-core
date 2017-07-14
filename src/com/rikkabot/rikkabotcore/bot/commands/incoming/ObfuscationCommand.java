package com.rikkabot.rikkabotcore.bot.commands.incoming;

import com.rikkabot.rikkabotcore.bot.commands.Command;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by piotr on 14.07.17.
 */
public class ObfuscationCommand extends Command {

    /**
     * Command ID.
     */
    public static final short ID = 26561;

    /**
     * Swf for encoding and decoding
     */
    public byte[] code;

    /**
     * Fake size of the code
     */
    public int size;

    public ObfuscationCommand(byte[] code, int size) {
        super(ObfuscationCommand.ID);

        this.code = code;
        this.size = size;
    }

    public ObfuscationCommand() {
        super(ObfuscationCommand.ID);
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
        output.write(this.code.length);
        output.write(this.code, 0, this.code.length);
        output.write(this.size << 9 | this.size >> 23);
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
        this.size = input.readInt();
        this.size = size >> 9 | size << 23;
    }
}
