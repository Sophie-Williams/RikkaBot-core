package com.rikkabot.rikkabotcore.bot;

import io.netty.channel.socket.SocketChannel;

import lombok.Data;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.Console;

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
    public GameConnection(SocketChannel ch) {
        this.channel(ch);

        Console.debug("Connected to game sever!");
    }
}
