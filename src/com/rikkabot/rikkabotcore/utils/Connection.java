package com.rikkabot.rikkabotcore.utils;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.SocketChannel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.Console;

/**
 * Generic connection.
 * ================
 *
 * Represents the end to end socket connection.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @AllArgsConstructor
public class Connection {
    /**
     * Channel connection.
     */
    private SocketChannel channel;

    /**
     * Sends a command through the channel.
     *
     * @param command Command to send
     */
    public void send(@NonNull Command command) {
        ByteBufOutputStream outputStream = this.getOutputStreamFor(command);

        Console.debug("Sending command "+ command.getClass().getName() +"...");
        this.channel()
            .writeAndFlush(outputStream.buffer())
            .addListener((f)->{
                if (!f.isSuccess()) {
                    Console.debug("Couldn't send the command!");
                    Console.debug(f.cause());

                    return;
                }

                Console.debug("Command sent: "+ command.getClass().getName());
            });
    }

    /**
     * Returns the output stream for a command.
     *
     * @param command Command to get output stream.
     *
     * @return Output stream for `command`.
     */
    protected ByteBufOutputStream getOutputStreamFor(Command command) {
        ByteBufOutputStream outputStream = new ByteBufOutputStream(Unpooled.buffer());

        try {
            command.write(outputStream);
        } catch(Exception e) {
            Console.print(e);
        }

        return outputStream;
    }
}
