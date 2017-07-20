package com.rikkabot.rikkabotcore.utils;

import java.io.IOException;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import lombok.Data;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.Console;

/**
 * Command lookup.
 * ===============
 *
 * Generic command lookup.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public abstract class CommandLookup extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * Available commands.
     */
    private Map<Short, Class> commands;

    /**
     * Handler lookup instance.
     */
    private HandlerLookup handler;

    /**
     * Connection instance.
     */
    private Connection connection;

    /**
     * Constructor.
     *
     * @param handler    Handler instance.
     * @param connection Connection instance.
     */
    public CommandLookup(HandlerLookup handler, Connection connection) {
        this.handler(handler)
            .connection(connection)
            .commands(this.getCommands());
    }

    /**
     * Finds and instances a command for given input stream.
     *
     * @param in Input stream with command data.
     *
     * @return Command for `in` or null.
     */
    protected Command findCommandFor(ByteBufInputStream in) {
        short id;

        try {
            id = in.readShort();
        } catch (IOException e) {
            Console.print(e);

            return null;
        }

        Class commandClass = this.commands().get(id);
        if (commandClass == null) {
            Console.debug("Received unknown command with ID " + id + "!");

            return null;
        }

        Command command;
        try {
            command = (Command) commandClass.newInstance();
        } catch (Exception e) {
            Console.print(e);

            return null;
        }

        command.id(id);

        try {
            command.read(in);
        } catch (Exception e) {
            Console.print(e);

            return null;
        }

        return command;
    }

    /**
     * <strong>Please keep in mind that this method will be renamed to
     * {@code messageReceived(ChannelHandlerContext, ByteBuf)} in 5.0.</strong>
     * <p>
     * Is called for each message of type {@link ByteBuf}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ByteBufInputStream inputStream = new ByteBufInputStream(msg);

        Command command = this.findCommandFor(inputStream);
        if (command == null) {
            return;
        }

        Console.debug("Received command: "+ command.getClass().getName());

        Handler handler = this.handler().handler(this.connection(), command);
        if (handler == null) {
            Console.debug("Received command with ID "+ command.id() +" doesn't have an associated handler!");

            return;
        }

        handler.handle();
    }

    /**
     * Builds and returns the map with available commands.
     *
     * @return Map with available commands.
     */
    public abstract Map<Short, Class> getCommands();
}
