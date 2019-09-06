package com.ultra.netty.tcp.server;

import com.ultra.conditional.RegisterConditional;
import com.ultra.util.StringUtil;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tcp Server处理器:建立连接,关闭连接,心跳,接收消息,发送消息
 *
 * @author admin
 */
@Component
@ChannelHandler.Sharable
@Conditional(value = RegisterConditional.class)
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("客户端channelId:{}连接", channel.id());
        }
    }

    /**
     * 关闭连接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ChannelId id = ctx.channel().id();
        channelMap.remove(id);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("客户端channelId:{}断开连接", id);
        }
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
                //LOGGER.info("Tcp Server长期未向客户端发送数据");
                sendMsgToTcpClient("server");
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
        LOGGER.info("channelId:{}", ctx.channel().id());
        String hexResult = (String) msg;
        LOGGER.info("hexResult:{}", hexResult);
        String result = StringUtil.hexStringToString(hexResult);
        LOGGER.info("result:{}", result);
    }

    /**
     * 向客户端发送消息
     *
     * @param msg 消息
     */
    public void sendMsgToTcpClient(String msg) {
        if (!channelMap.isEmpty()) {
            LOGGER.info("send message to client:{}", msg);
            channelMap.forEach((channelId, channel) -> {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("channelId:{}", channelId);
                }
                String result = StringUtil.stringToHexString(msg);
                channel.writeAndFlush(result);
            });
        } else {
            LOGGER.error("ChannelHandlerContext is null");
        }
    }

}
