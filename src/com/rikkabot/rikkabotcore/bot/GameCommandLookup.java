package com.rikkabot.rikkabotcore.bot;

import java.io.IOException;
import java.util.HashMap;

import com.rikkabot.rikkabotcore.bot.commands.incoming.HandshakeCommand;
import com.rikkabot.rikkabotcore.bot.commands.incoming.ObfuscationCommand;
import com.rikkabot.rikkabotcore.bot.commands.incoming.VersionCommand;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import lombok.Data;
import lombok.experimental.Accessors;

import com.manulaiko.tabitha.Console;

import com.rikkabot.rikkabotcore.bot.commands.Command;
import com.rikkabot.rikkabotcore.bot.handlers.Handler;

/**
 * Game command lookup.
 * ====================
 * <p>
 * Lookup
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class GameCommandLookup extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * Game connection instance.
     */
    private GameConnection connection;

    /**
     * Available commands.
     */
    private HashMap<Short, Class> commands = new HashMap<>();

    /**
     * Constructor.
     *
     * @param connection Game connection;
     */
    public GameCommandLookup(GameConnection connection) {
        this.connection(connection);

        this.commands.put(VersionCommand.ID, VersionCommand.class);
        this.commands.put(HandshakeCommand.ID, HandshakeCommand.class);
        this.commands.put(ObfuscationCommand.ID, ObfuscationCommand.class);
    }

    /**
     * Finds and instances a command for given input stream.
     *
     * @param in Input stream with command data.
     * @return Command for `in` or null.
     */
    private Command findCommandFor(ByteBufInputStream in) {
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

        Handler handler = GameHandlerLookup.instance().handler(this.connection(), command);
        if (handler == null) {
            Console.debug("Received command with ID "+ command.id() +" doesn't have an associated handler!");

            return;
        }

        handler.handle();
    }
}
