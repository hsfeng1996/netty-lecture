package com.hsfeng.nettydemo.dns;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class DnsServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap server = new Bootstrap();
            server.group(bossGroup)
                    .channel(NioDatagramChannel.class)
                    .handler(new DnsServerChannelInitializer())
                    .option(ChannelOption.SO_BROADCAST, true);

            ChannelFuture future = server.bind(53).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
        }
    }
}
