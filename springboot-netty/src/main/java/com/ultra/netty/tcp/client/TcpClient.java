package com.ultra.netty.tcp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class TcpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TcpClient.class);

    @Value(("${netty.tcp.server.host}"))
    private String host;
    @Value("${netty.tcp.server.port}")
    private int port;
    @Autowired
    private ClientChannelInitializer clientChannelInitializer;
    @Autowired
    private ClientChannelHandler clientChannelHandler;

    // 通过nio方式来接收连接和处理连接
    private EventLoopGroup group = new NioEventLoopGroup();

    /**
     * 初始化 `Bootstrap` 客户端引导程序
     */
    @PostConstruct
    public void run() {
       new Thread(() -> {
           reConnect(group);
       },"Tcp Client init").start();
    }

    /**
     * 建立连接,获取连接通道对象,重连机制
     * @param eventLoopGroup
     */
    public void reConnect(EventLoopGroup eventLoopGroup) {
        Bootstrap bootstrap = new Bootstrap();
        try {
            if (bootstrap != null) {
                bootstrap.group(eventLoopGroup);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
                bootstrap.handler(clientChannelInitializer);
                bootstrap.remoteAddress(host, port);
                ChannelFuture channelFuture = bootstrap.connect().addListener((ChannelFuture futureListener) -> {
                    final EventLoop eventLoop = futureListener.channel().eventLoop();
                    if (!futureListener.isSuccess()) {
                        LOGGER.info("与服务端断开连接!在10s之后准备尝试重连!");
                        eventLoop.schedule(() -> reConnect(eventLoop), 10, TimeUnit.SECONDS);
                    }else {
                        LOGGER.info("Netty客户端启动成功!");
                    }
                });
                // 阻塞
                //channelFuture.channel().closeFuture().sync();
            }
        } catch (Exception e) {
            LOGGER.error("客户端连接失败!",e.getMessage());
        }
    }

}