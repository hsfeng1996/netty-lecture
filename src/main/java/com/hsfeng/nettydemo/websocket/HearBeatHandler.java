package com.hsfeng.nettydemo.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HearBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;

            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("read idle");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("write idle");
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("all idle");
                ctx.channel().close();
            }
        }
    }
}
