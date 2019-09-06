package com.ultra.netty.tcp.client;

import com.ultra.conditional.RegisterConditional;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 通道初始化:设置编码,解码,心跳频率,Handler
 *
 * @author admin
 */
@Component
@Conditional(RegisterConditional.class)
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ClientChannelHandler clientChannelHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        //ByteBuf delimiter = Unpooled.copiedBuffer("$".getBytes());
        //channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(2048, delimiter));
        //channelPipeline.addLast(new LineBasedFrameDecoder(1024 * 5));
        channelPipeline.addLast(new StringDecoder());
        channelPipeline.addLast(new StringEncoder());
        //channelPipeline.addLast(new FixedLengthFrameDecoder(90));
        //检测 接收/读数据,输入/写数据,既未读又未写数据时间间隔
        channelPipeline.addLast(new IdleStateHandler(60, 20, 600, TimeUnit.SECONDS));
        //自定义Handler
        channelPipeline.addLast(clientChannelHandler);
    }
}