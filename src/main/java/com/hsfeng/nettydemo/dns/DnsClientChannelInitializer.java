package com.hsfeng.nettydemo.dns;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.dns.DatagramDnsQueryEncoder;
import io.netty.handler.codec.dns.DatagramDnsResponseDecoder;


public class DnsClientChannelInitializer extends ChannelInitializer<NioDatagramChannel> {

    @Override
    protected void initChannel(NioDatagramChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(DatagramDnsQueryEncoder.class.getName(), new DatagramDnsQueryEncoder());
        pipeline.addLast(DatagramDnsResponseDecoder.class.getName(), new DatagramDnsResponseDecoder());
        pipeline.addLast(DnsClientHandler.class.getName(), new DnsClientHandler());
    }
}
