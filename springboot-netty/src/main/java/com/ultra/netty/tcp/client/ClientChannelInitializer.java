package com.ultra.netty.tcp.client;

//import com.h3c.iot.app.engine.netty.tcp.server.ServerChannelHandler;

import com.ultra.netty.tcp.codec.HexDecoder;
import com.ultra.netty.tcp.codec.HexEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
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
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        //ByteBuf delimiter = Unpooled.copiedBuffer("\\$".getBytes());
        //p.addLast("framer", new DelimiterBasedFrameDecoder(2048, delimiter));
        //channelPipeline.addLast(new LineBasedFrameDecoder(1024 * 5));
        channelPipeline.addLast("decoder", new HexDecoder());
        channelPipeline.addLast("encoder", new HexEncoder());
        channelPipeline.addLast("frameDecoder", new FixedLengthFrameDecoder(90));
        //检测 接收/读数据,输入/写数据,既未读又未写数据时间间隔
        channelPipeline.addLast("idleStateHandler", new IdleStateHandler(60, 20, 600, TimeUnit.SECONDS));
        //channelPipeline.addLast(clientChannelHandler);
        //自定义Handler
        channelPipeline.addLast("clientChannelHandler", clientChannelHandler);
    }
}