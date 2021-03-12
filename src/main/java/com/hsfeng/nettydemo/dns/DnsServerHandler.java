package com.hsfeng.nettydemo.dns;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.dns.*;

import java.util.HashMap;
import java.util.Map;

public class DnsServerHandler extends SimpleChannelInboundHandler<DatagramDnsQuery> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramDnsQuery msg) throws Exception {
        Map<String, byte[]> ipMap = new HashMap<>();
        ipMap.put("www.baidu.com.", new byte[]{61, (byte)135, (byte)168, 125});

        DatagramDnsResponse response = new DatagramDnsResponse(msg.recipient(), msg.sender(), msg.id());
        try {
            DefaultDnsQuestion dnsQuestion = msg.recordAt(DnsSection.QUESTION);
            response.addRecord(DnsSection.QUESTION, dnsQuestion);
            System.out.println(dnsQuestion.name());

            ByteBuf buf = null;
            System.out.println(dnsQuestion.name());
            if (ipMap.containsKey(dnsQuestion.name())) {
                buf = Unpooled.wrappedBuffer(ipMap.get(dnsQuestion.name()));
            } else {
                buf = Unpooled.wrappedBuffer(new byte[]{0, 0, 0, 0});
            }

            DefaultDnsRawRecord queryAnswer = new DefaultDnsRawRecord(dnsQuestion.name(), DnsRecordType.A, 10,  buf);
            response.addRecord(DnsSection.ANSWER, queryAnswer);

            ctx.writeAndFlush(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            ctx.close();
        }
    }
}
