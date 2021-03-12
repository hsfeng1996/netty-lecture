package com.hsfeng.nettydemo.factorial;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;

public class FactorialClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP));
        pipeline.addLast(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));
        pipeline.addLast(BigIntegerDecoder.class.getName(), new BigIntegerDecoder());
        pipeline.addLast(BigIntegerEncoder.class.getName(), new BigIntegerEncoder());
        pipeline.addLast(FactorialClientHandler.class.getName(), new FactorialClientHandler());
    }
}
