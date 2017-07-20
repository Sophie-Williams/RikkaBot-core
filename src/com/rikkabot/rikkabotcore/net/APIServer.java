package com.rikkabot.rikkabotcore.net;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import lombok.Data;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.Console;

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
@Accessors @Data
public class APIServer {
    /**
     * Local port to connect.
     */
    private int port;

    /**
     * Whether we should accept connections from external clients or not.
     */
    private boolean isPublic;

    /**
     * Channel pipeline.
     */
    private GamePipeline pipeline = new GamePipeline();

    /**
     * Constructor.
     */
    public APIServer(int port, boolean isPublic) {
        this.port(port)
            .isPublic(isPublic);
    }

    /**
     * Starts listening for connections.
     */
    public void start() {
        EventLoopGroup bossGroup   = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        InetSocketAddress address = this.address();

        ServerBootstrap server = new ServerBootstrap();
        server.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new APIPipeline())
                .bind(address)
                .addListener((f)->{
                    Console.debug("API server listening on " + address);
                });
    }

    /**
     * Builds and returns the InetSocketAddress the server will listen.
     *
     * @return InetSocketAddress the server will bind.
     */
    private InetSocketAddress address() {
        if(this.isPublic()) {
            return new InetSocketAddress(this.port());
        }

        return new InetSocketAddress("127.0.0.1", this.port());
    }
}
