package com.hsfeng.nettydemo.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boss = new NioEventLoopGroup();
        try {
            Bootstrap client = new Bootstrap();
            client.group(boss)
                    .channel(NioSocketChannel.class)
                    .handler(new EchoClientChannelInitializer());
            ChannelFuture future = client.connect("127.0.0.1", 8090).sync();
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
        }
    }
}
