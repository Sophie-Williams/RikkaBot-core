package com.rikkabot.rikkabotcore.bot;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.SocketChannel;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.bot.commands.Command;
import com.rikkabot.rikkabotcore.bot.middlewares.EncryptionMiddleware;
import com.rikkabot.rikkabotcore.dao.hero.Hero;
import com.rikkabot.rikkabotcore.utils.Connection;

/**
 * Game connection.
 * ================
 *
 * Represents the end to end connection between the bot and the server.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class GameConnection extends Connection {
    /**
     * Connection hero.
     */
    private Hero hero;

    /**
     * Constructor.
     *
     * @param ch Socket channel.
     */
    public GameConnection(@NonNull SocketChannel ch, @NonNull Hero hero) {
        super(ch);

        this.hero(hero);

        hero.gameConnection(this);
    }

    /**
     * Fired when the connection has been stabilised.
     */
    public void onConnected() {
        Console.debug("Connected to game sever!");

        this.hero().onConnected();
    }

    /**
     * Returns the output stream for a command.
     *
     * @param command Command to get output stream.
     *
     * @return Output stream for `command`.
     */
    @Override
    protected ByteBufOutputStream getOutputStreamFor(com.rikkabot.rikkabotcore.utils.Command command) {
        ByteBufOutputStream outputStream = new ByteBufOutputStream(Unpooled.buffer());

        try {
            command.write(outputStream);

            byte[] bytes = outputStream.buffer().array();
            if (((Command)command).needsEncryption()) {
                bytes = EncryptionMiddleware.encrypt(bytes);
            }

            outputStream.buffer().clear();
            outputStream.buffer().writeBytes(bytes);
        } catch(Exception e) {
            Console.print(e);
        }

        return outputStream;
    }
}
