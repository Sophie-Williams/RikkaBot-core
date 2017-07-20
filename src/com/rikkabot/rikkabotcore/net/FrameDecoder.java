package com.rikkabot.rikkabotcore.net;


import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

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
