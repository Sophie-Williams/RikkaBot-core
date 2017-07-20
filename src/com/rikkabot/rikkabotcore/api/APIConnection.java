package com.rikkabot.rikkabotcore.api;

import io.netty.channel.socket.SocketChannel;

import lombok.Data;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.utils.Connection;

/**
 * API Connection.
 * ===============
 *
 * Connection for each API socket.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class APIConnection  extends Connection {
    /**
     * Constructor.
     *
     * @param channel Socket channel.
     */
    public APIConnection(SocketChannel channel) {
        super(channel);
    }
}
