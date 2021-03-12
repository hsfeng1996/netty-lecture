package com.hsfeng.nettydemo.dns;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.dns.DatagramDnsQueryDecoder;
import io.netty.handler.codec.dns.DatagramDnsResponseEncoder;

public class DnsServerChannelInitializer extends ChannelInitializer<NioDatagramChannel> {

    @Override
    protected void initChannel(NioDatagramChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(DatagramDnsQueryDecoder.class.getName(), new DatagramDnsQueryDecoder());
        pipeline.addLast(DatagramDnsResponseEncoder.class.getName(), new DatagramDnsResponseEncoder());
        pipeline.addLast(DnsServerHandler.class.getName(), new DnsServerHandler());
    }
}
