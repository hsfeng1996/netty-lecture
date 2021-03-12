package com.hsfeng.nettydemo.time;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class TimeClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(TimeDecoder.class.getName(), new TimeDecoder());
        pipeline.addLast(TimeClientHandler.class.getName(), new TimeClientHandler());
    }
}
