package com.hsfeng.nettydemo.echo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


public class EchoServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("echo", new EchoServerHandler());
    }
}
