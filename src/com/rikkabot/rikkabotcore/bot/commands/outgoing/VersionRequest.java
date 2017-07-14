package com.rikkabot.rikkabotcore.bot.commands.outgoing;

import com.rikkabot.rikkabotcore.bot.commands.Command;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by piotr on 14.07.17.
 */
public class VersionRequest extends Command {

    /**
     * Command ID.
     */
    public static final short ID = 666;

    /**
     * Major of the main.swf
     */
    private int major;

    /**
     * Minor of the main.swf
     */
    private int minor;

    /**
     * Build of the main.swf
     */
    private int build;

    public VersionRequest(int major, int minor, int build) {
        super(VersionRequest.ID);

        this.major = major;
        this.minor = minor;
        this.build = build;
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
        output.writeInt(this.major);
        output.writeInt(this.minor);
        output.writeInt(this.build);
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
        this.major = input.readInt();
        this.minor = input.readInt();
        this.build = input.readInt();
    }
}
