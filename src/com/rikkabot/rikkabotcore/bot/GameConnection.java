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

/**
 * Game connection.
 * ================
 *
 * Represents the end to end connection between the bot and the server.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class GameConnection {
    /**
     * Channel connection.
     */
    private SocketChannel channel;

    /**
     * Constructor.
     *
     * @param ch Socket channel.
     */
    public GameConnection(@NonNull SocketChannel ch) {
        this.channel(ch);

        Console.debug("Connected to game sever!");
    }

    /**
     * Sends a command through the channel.
     *
     * @param command Command to send
     */
    public void send(@NonNull Command command) {
        try {
            ByteBufOutputStream outputStream = new ByteBufOutputStream(Unpooled.buffer());
            command.write(outputStream);

            byte[] bytes = outputStream.buffer().array();
            if (command.needsEncryption()) {
                bytes = EncryptionMiddleware.encrypt(bytes);
            }

            this.channel()
                .writeAndFlush(bytes)
                .addListener((f)->{
                    Console.debug("Command sent: "+ command.getClass().getName());
                });
        } catch (Exception e) {
            Console.print(e);
        }
    }
}
