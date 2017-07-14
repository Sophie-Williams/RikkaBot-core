package com.rikkabot.rikkabotcore.bot.commands.outgoing;

import com.rikkabot.rikkabotcore.bot.commands.Command;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by piotr on 14.07.17.
 */
public class HandshakeRequest extends Command {

    /**
     * Command ID.
     */
    public static final short ID = 13320;

    /**
     * Generated secret key
     */
    public static byte[] secretKey;

    public HandshakeRequest(byte[] secretKey) {
        super(HandshakeRequest.ID);

        this.secretKey = secretKey;
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
        output.writeShort(-11537);
        output.writeInt(this.secretKey.length);
        output.write(this.secretKey);
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
        int size = input.readInt();
        this.secretKey = new byte[size];
        input.readFully(this.secretKey, 0, size);
    }
}
