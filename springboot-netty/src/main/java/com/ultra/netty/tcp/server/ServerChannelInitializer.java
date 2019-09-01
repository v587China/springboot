package com.ultra.netty.tcp.server;

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
 * 通道策略初始化
 *
 * @author admin
 */
@Component
@Conditional(value = TcpServerConditional.class)
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ServerChannelHandler serverChannelHandler = new ServerChannelHandler();

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //获取管道
        ChannelPipeline pipeline = ch.pipeline();
        //字符串解码器
        //pipeline.addLast(new HexDecoder());
        pipeline.addLast(new StringDecoder());
        //字符串编码器
        //pipeline.addLast(new HexEncoder());
        pipeline.addLast(new StringEncoder());
        //IdleStateHandler心跳机制,如果超时触发Handle中userEventTrigger()方法
        pipeline.addLast(new IdleStateHandler(60, 20, 0, TimeUnit.SECONDS));
        //处理类
        pipeline.addLast(serverChannelHandler);
    }

}
