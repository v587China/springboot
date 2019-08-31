package com.ultra.netty.tcp.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerChannelHandler.class);
    private Map<ChannelId, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 建立连接时
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channelMap.put(channel.id(), channel);
        ctx.fireChannelActive();
    }

    /**
     * 关闭连接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        channelMap.remove(ctx.channel().id());
    }

    /**
     * 心跳请求处理
     * 每20秒发送一次心跳请求;
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object obj) throws Exception {
        super.userEventTriggered(ctx, obj);
        if (obj instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) obj;
            if (event.state().equals(IdleState.READER_IDLE)) {
                LOGGER.info("Tcp Server长期没收到客户端推送数据");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                LOGGER.info("Tcp Server长期未向客户端发送数据");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                LOGGER.info("Tcp Server ALL");
            }
        }
    }

    /**
     * 接收消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String result = (String) msg;
        LOGGER.info("channelId:{},msg:{}", ctx.channel().id(), result);
    }

    /**
     * 向服务器发送消息
     *
     * @param msg 消息
     */
    public void sendMsgToTcpServer(String msg) {
        if (!channelMap.isEmpty()) {
            LOGGER.info("send message to server : {}", msg);
            channelMap.forEach((channelId, channel) -> {
                channel.writeAndFlush(msg);
            });
        } else {
            LOGGER.error("ChannelHandlerContext is null");
        }
    }

}
