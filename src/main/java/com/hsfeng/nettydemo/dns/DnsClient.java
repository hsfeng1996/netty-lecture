package com.hsfeng.nettydemo.dns;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class DnsClient {

    public static void main(String[] args) {
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap client = new Bootstrap();
            client.group(loopGroup)
                    .channel(NioDatagramChannel.class)
                    .handler(new DnsClientChannelInitializer());

            ChannelFuture future = client.connect("127.0.0.1", 53).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
