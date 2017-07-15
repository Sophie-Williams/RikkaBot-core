package com.rikkabot.rikkabotcore.net;

import com.manulaiko.tabitha.Console;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import lombok.Data;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.dao.hero.Hero;

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
     * Constructor.
     */
    public GameClient(String address, int port) {
        this.address(address)
            .port(port);
    }

    /**
     * Connects to the remote address.
     *
     * @param hero Hero that's going to connect.
     */
    public void connect(Hero hero) {
        Console.debug("Stabilising a connection to "+ this.address() +" on port "+ this.port() +"...");

        this.pipeline().hero(hero);

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap boot = new Bootstrap();
        boot.group(workerGroup)
            .channel(NioSocketChannel.class)
            .handler(this.pipeline())
            .connect(this.address(), this.port())
            .addListener((f)->{
                if(!f.isSuccess()) {
                    Console.debug("Couldn't connect to "+ this.address() +" on port "+ this.port() +"!");
                    Console.debug(f.cause());

                    return;
                }

                Console.debug("Connected to "+ this.address() +" on port "+ this.port() +"!");

                this.pipeline().connection().onConnected();
            });
    }
}
