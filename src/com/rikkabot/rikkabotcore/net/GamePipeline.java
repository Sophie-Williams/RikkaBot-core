package com.rikkabot.rikkabotcore.net;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import lombok.Data;
import lombok.experimental.Accessors;

import com.rikkabot.rikkabotcore.bot.GameCommandLookup;
import com.rikkabot.rikkabotcore.bot.GameConnection;
import com.rikkabot.rikkabotcore.dao.hero.Hero;

/**
 * Game pipeline.
 * ==============
 *
 * Pipeline for the game connection.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Accessors @Data
public class GamePipeline extends ChannelInitializer<SocketChannel> {
    /**
     * Hero that's going to connect.
     */
    private Hero hero;

    /**
     * Game connection.
     */
    private GameConnection connection;

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
        this.connection(new GameConnection(ch, this.hero()));

        pipeline.addLast("frameDecoder", new FrameDecoder())
                //.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8))
                //.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8))
                .addLast("commandLookup", new GameCommandLookup(this.connection()))
                .addLast("lengthPrepender", new LengthFieldPrepender(Short.BYTES));
    }

    /**
     * Game frame decoder.
     * ===================
     *
     * Decodes a received frame.
     *
     * @author Manulaiko <manulaiko@gmail.com>
     */
    public class FrameDecoder extends ByteToMessageDecoder {
        /**
         * Decode the from one {@link ByteBuf} to an other. This method will be called till either the input
         * {@link ByteBuf} has nothing to read when return from this method or till nothing was read from the input
         * {@link ByteBuf}.
         *
         * @param ctx the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
         * @param in  the {@link ByteBuf} from which to read data
         * @param out the {@link List} to which decoded messages should be added
         * @throws Exception is thrown if an error accour
         */
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            if (in.readableBytes() < Short.BYTES) {
                return;
            }

            in.markReaderIndex();
            int frameLength = in.readUnsignedShort();

            if (in.readableBytes() < frameLength) {
                in.resetReaderIndex();

                return;
            }

            out.add(in.readSlice(frameLength)
                    .retain());
        }
    }
}
