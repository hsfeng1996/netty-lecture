package com.hsfeng.nettydemo.sixth;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HttpServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

//        pipeline.addLast(HttpServerCodec.class.getName(), new HttpServerCodec());
        pipeline.addLast(HttpRequestDecoder.class.getName(), new HttpRequestDecoder());
        pipeline.addLast(HttpResponseEncoder.class.getName(), new HttpResponseEncoder());
        pipeline.addLast(HttpServerHandler.class.getName(), new HttpServerHandler());
    }
}
