package com.ultra.netty.tcp.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ClientChannelHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientChannelHandler.class);
    @Autowired
    private TcpClient tcpClient;
    private ChannelHandlerContext ctx;
    /**
     * 建立连接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx=ctx;
        ctx.fireChannelActive();
    }

    /**
     * 关闭连接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final EventLoop eventLoop = ctx.channel().eventLoop();
        tcpClient.reConnect(eventLoop);
        super.channelInactive(ctx);
    }

    /**
     * 心跳请求处理
     * 每4秒发送一次心跳请求;
     *
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        super.userEventTriggered(ctx, obj);
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            if (event.state().equals(IdleState.READER_IDLE)) {
                LOGGER.info("长期没收到服务器推送数据");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                LOGGER.info("长期未向服务器发送数据");
                // 发送心跳包
                // sendMsgToTcpServer("ping");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                LOGGER.info("ALL");
            }
        }
    }

    /**
     * 接收消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("receive message from server : {}",msg);
    }

    /**
     * 向服务器发送消息
     * @param msg
     */
    public void sendMsgToTcpServer(String msg) {
        if (ctx != null) {
            LOGGER.info("send message to server : {}", msg);
            this.ctx.channel().writeAndFlush(msg);
        } else {
            LOGGER.error("ChannelHandlerContext is null");
        }
    }

}