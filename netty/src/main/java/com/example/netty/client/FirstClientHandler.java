package com.example.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端写出数据");

        // 获取数据
        ByteBuf buffer = getByteBuf(ctx);

        // 写数据
        ctx.channel().write(buffer);

    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();

        // 2. 准备数据，指定字符串的字符集为utf-8
        byte[] bytes = "你好，我来自客户端".getBytes(Charset.forName("utf-8"));

        // 3. 填充数据到ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }
}
