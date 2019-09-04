package com.ultra.netty.tcp.client;

import com.ultra.util.StringUtil;
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

/**
 * Tcp Client处理器:建立连接,关闭连接,心跳,接收消息,发送消息
 *
 * @author admin
 */
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
    public void channelActive(ChannelHandlerContext ctx) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("channelId:{}", ctx.channel().id());
        }
        this.ctx = ctx;
        ctx.fireChannelActive();
    }

    /**
     * 关闭连接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final EventLoop eventLoop = ctx.channel().eventLoop();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("channelId:{}", ctx.channel().id());
        }
        tcpClient.reConnect(eventLoop);
        super.channelInactive(ctx);
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
                LOGGER.info("Tcp Client长期没收到服务器推送数据");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                //LOGGER.info("Tcp Client heartbeat");
                // 发送心跳包
                sendMsgToTcpServer("client");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                LOGGER.info("Tcp Client ALL");
            }
        }
    }

    /**
     * 接收消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String hexResult = (String) msg;
        LOGGER.info("hexResult:{}", hexResult);
        String result = StringUtil.hexStringToString(hexResult);
        LOGGER.info("result:{}", result);
    }


    /**
     * 向服务器发送消息
     *
     * @param msg 消息
     */
    public void sendMsgToTcpServer(String msg) {
        if (ctx != null) {
            LOGGER.info("send message to server:{}", msg);
            String result = StringUtil.stringToHexString(msg);
            this.ctx.channel().writeAndFlush(result);
        } else {
            LOGGER.error("ChannelHandlerContext is null");
        }
    }


}