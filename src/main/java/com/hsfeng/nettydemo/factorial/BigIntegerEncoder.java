package com.hsfeng.nettydemo.factorial;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.math.BigInteger;

public class BigIntegerEncoder extends MessageToByteEncoder<Number> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Number msg, ByteBuf out) throws Exception {
        BigInteger bi;
        if (msg instanceof BigInteger) {
            bi = (BigInteger) msg;
        } else {
            bi = new BigInteger(String.valueOf(msg));
        }

        byte[] data = bi.toByteArray();
        int dataLength = data.length;

        out.writeByte((byte)'F');
        out.writeInt(dataLength);
        out.writeBytes(data);
    }
}
