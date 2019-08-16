package com.ultra.netty.tcp.client;

//import com.h3c.iot.app.engine.netty.tcp.server.ServerChannelHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 通道初始化，主要用于设置各种Handler
 */
@Component
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ClientChannelHandler clientChannelHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        // ByteBuf delimiter = Unpooled.copiedBuffer("\\$".getBytes());
        // p.addLast("framer", new DelimiterBasedFrameDecoder(2048, delimiter));
        // channelPipeline.addLast(new LineBasedFrameDecoder(1024 * 5));
        channelPipeline.addLast("frameDecoder", new FixedLengthFrameDecoder(100));
        channelPipeline.addLast("idleStateHandler", new IdleStateHandler(60, 20, 60 * 10, TimeUnit.SECONDS));
        channelPipeline.addLast(clientChannelHandler);
        //自定义Handler
        channelPipeline.addLast("clientChannelHandler", clientChannelHandler);
    }
}