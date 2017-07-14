package com.rikkabot.rikkabotcore.net;

import com.manulaiko.tabitha.Console;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Game client.
 * ============
 *
 * Client connection to the game server.
 *
 * It connects to the game server and maintains the communication.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @RequiredArgsConstructor
public class GameClient {
    /**
     * Remote address to connect.
     */
    private String address;

    /**
     * Remote port to connect.
     */
    private int port;

    /**
     * Channel pipeline.
     */
    private GamePipeline pipeline = new GamePipeline();

    /**
     * Connects to the remote address.
     */
    public boolean connect() {
        Console.debug("Stabilising a connection to "+ this.address() +" on port "+ this.port() +"...");


        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        boot.group(workerGroup)
            .channel(NioSocketChannel.class)
            .handler(this.pipeline())
            .connect(this.address(), this.port());
    }
}
