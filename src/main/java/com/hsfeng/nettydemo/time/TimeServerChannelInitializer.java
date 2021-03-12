package com.hsfeng.nettydemo.time;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class TimeServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(TimeEncoder.class.getName(), new TimeEncoder());
        pipeline.addLast(TimeServerHandler.class.getName(), new TimeServerHandler());
    }
}
