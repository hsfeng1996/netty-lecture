package com.hsfeng.nettydemo.factorial;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.math.BigInteger;

public class FactorialClientHandler extends SimpleChannelInboundHandler<BigInteger> {

    private static int next = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(++next);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BigInteger msg) throws Exception {
        System.out.printf("Factorial of %,d is: %,d%n", next, msg);
        if (next == 10) {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
            ctx.close();
        } else {
            ctx.writeAndFlush(++next);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
