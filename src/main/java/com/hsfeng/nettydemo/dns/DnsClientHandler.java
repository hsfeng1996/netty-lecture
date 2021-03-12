package com.hsfeng.nettydemo.dns;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.dns.*;
import io.netty.util.NetUtil;

import java.net.InetSocketAddress;

public class DnsClientHandler extends SimpleChannelInboundHandler<DatagramDnsResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DnsQuery query = new DatagramDnsQuery(new InetSocketAddress("127.0.0.1", 8090),
                new InetSocketAddress("127.0.0.1", 53), 1).setRecord(
                DnsSection.QUESTION, new DefaultDnsQuestion("www.baidu.com", DnsRecordType.A));
        System.out.println(query.recordAt(DnsSection.QUESTION).name());
        ctx.writeAndFlush(query);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramDnsResponse msg) throws Exception {
        if (msg.count(DnsSection.QUESTION) > 0) {
            DnsQuestion question = msg.recordAt(DnsSection.QUESTION, 0);
            System.out.printf("name: %s%n", question.name());
        }
        for (int i = 0, count = msg.count(DnsSection.ANSWER); i < count; i++) {
            DnsRecord record = msg.recordAt(DnsSection.ANSWER, i);
            if (record.type() == DnsRecordType.A) {
                //just print the IP after query
                DnsRawRecord raw = (DnsRawRecord) record;
                System.out.println(NetUtil.bytesToIpAddress(ByteBufUtil.getBytes(raw.content())));
            }
        }
        ctx.close();
    }
}
