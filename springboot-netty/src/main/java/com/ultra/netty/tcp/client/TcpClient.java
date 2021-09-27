package com.ultra.netty.tcp.client;

import com.ultra.netty.assembly.BeanRegisterConditional;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Tcp Client配置
 *
 * @author admin
 */
@Component
@Conditional(value = BeanRegisterConditional.class)
public class TcpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TcpClient.class);

    @Value(("${tcp.server.host}"))
    private String host;
    @Value("${tcp.server.port}")
    private int port;
    @Autowired
    private TcpClientChannelInitializer clientChannelInitializer;

    /**
     * 通过nio方式来接收连接和处理连接
     */
    private EventLoopGroup group = new NioEventLoopGroup();

    /**
     * 初始化 `Bootstrap` 客户端引导程序
     */
    @PostConstruct
    public void run() {
        new Thread(() -> {
            reConnect(group);
        }, "Tcp Client").start();
    }

    /**
     * 建立连接,获取连接通道对象,重连机制
     */
    void reConnect(EventLoopGroup group) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(clientChannelInitializer);
            bootstrap.remoteAddress(host, port);
            // 阻塞式/同步 放开
            //ChannelFuture channelFuture =
            bootstrap.connect().addListener((ChannelFuture futureListener) -> {
                if (!futureListener.isSuccess()) {
                    final EventLoop eventLoop = futureListener.channel().eventLoop();
                    LOGGER.info("与服务端断开连接!在10s之后准备尝试重连!");
                    eventLoop.schedule(() -> reConnect(group), 10, TimeUnit.SECONDS);
                } else {
                    LOGGER.info("Netty客户端启动成功!");
                }
            });
            // 阻塞/同步
            //channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            LOGGER.error("客户端连接失败!", e);
        }
    }

}