package com.rikkabot.rikkabotcore.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import com.rikkabot.rikkabotcore.bot.GameCommandLookup;
import com.rikkabot.rikkabotcore.bot.GameConnection;

/**
 * Game pipeline.
 * ==============
 *
 * Pipeline for the game connection.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class GamePipeline extends ChannelInitializer<SocketChannel> {
    /**
     * This method will be called once the {@link Channel} was registered. After the method returns this instance
     * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
     *
     * @param ch the {@link Channel} which was registered.
     * @throws Exception is thrown if an error occurs. In that case it will be handled by
     *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
     *                   the {@link Channel}.
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline   = ch.pipeline();
        GameConnection  connection = new GameConnection(ch);

        pipeline.addLast("frameDecoder", new DelimiterBasedFrameDecoder(1024, Delimiters.nulDelimiter()))
                .addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8))
                .addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8))
                .addLast("commandLookup", new GameCommandLookup(connection));
    }
}
