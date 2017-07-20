package com.rikkabot.rikkabotcore.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.api.APICommandLookup;
import com.rikkabot.rikkabotcore.api.APIConnection;

/**
 * API pipeline.
 * =============
 *
 * Pipeline for all the API connections.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data @AllArgsConstructor
public class APIPipeline extends ChannelInitializer<SocketChannel> {
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
        ChannelPipeline pipeline = ch.pipeline();
        APIConnection gameConnection = new APIConnection(ch);

        pipeline.addLast("frameDecoder", new FrameDecoder());
        pipeline.addLast("commandLookup", new APICommandLookup(gameConnection));
        pipeline.addLast("lengthPrepender", new LengthFieldPrepender(Short.BYTES));
    }
}
