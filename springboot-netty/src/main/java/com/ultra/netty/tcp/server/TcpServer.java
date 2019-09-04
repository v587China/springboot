package com.ultra.netty.tcp.server;

import com.ultra.conditional.FalseConditional;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Tcp Server 配置
 *
 * @author admin
 */
@Component
@Conditional(value = FalseConditional.class)
public class TcpServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(TcpServer.class);
    @Autowired
    private ServerChannelInitializer serverChannelInitializer;
    @Value("${netty.tcp.server.host}")
    private String host;
    @Value("${netty.tcp.server.port:10002}")
    private int port;

    @PostConstruct
    private void init() {
        //boss线程监听端口，worker线程负责数据读写
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            //辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            //设置线程池
            bootstrap.group(boss, worker);
            //设置socket工厂
            bootstrap.channel(NioServerSocketChannel.class);
            //设置TCP参数
            //1.链接缓冲池的大小（ServerSocketChannel的设置）
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            //关闭延迟发送
            //bootstrap.option(ChannelOption.TCP_NODELAY, true);
            //维持链接的活跃，清除死链接(SocketChannel的设置)
            //长连接
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            //设置管道工厂
            bootstrap.childHandler(serverChannelInitializer);
            //绑定端口
            ChannelFuture f = bootstrap.bind(host, port).sync();
            if (f.isSuccess()) {
                LOGGER.info("启动Netty服务成功,端口号:{}", port);
            }
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            LOGGER.error("启动Netty服务异常.", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}