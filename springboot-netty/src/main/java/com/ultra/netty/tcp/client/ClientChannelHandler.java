package com.ultra.netty.tcp.client;

import io.netty.buffer.ByteBufUtil;
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
        this.ctx = ctx;
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
        String result = (String) msg;
        int total = 0;
        int current = 1;
        int sum = 0;
        StringBuilder yDistance = new StringBuilder();
        StringBuilder xDistance = new StringBuilder();
        String[] arrays = result.split(" ");
        for (int i = 0; i < 100; i++) {
            String hexString = arrays[i];
            if (i == 46) {
                LOGGER.info("sum:" + sum);
                LOGGER.info("sum:" + Integer.parseInt(hexString, 16));
            } else {
                sum += Integer.parseInt(hexString, 16);
                if (i == 18) {
                    total = Integer.parseInt(hexString, 16);
                    if (total > 0) {
                        LOGGER.info("一共" + total + "个目标.");
                    }
                } else if (i > 18 && total >= current) {
                    if ((i - 8) % 6 == 1) {
                        LOGGER.info("第" + current + "个目标信息:");
                        yDistance.append(hexString);
                    } else if ((i - 8) % 6 == 2) {
                        yDistance.insert(0, hexString);
                        /**
                         * Y轴距离为实际距离单位（米），占两个字节，无符号数据，低字节在前，高字节在后，范围为0~65535（为了兼容其它雷达，测量范围比较宽），分辨率为0.1m，表示的最大距离为6553.5m。例如1000表示距离为100m。
                         */
                        LOGGER.info("Y轴:" + ((double) Integer.parseInt(yDistance.toString(), 16)) / 10 + "m");
                    } else if ((i - 8) % 6 == 3) {
                        /**
                         * 单位为m/s，有符号数据，范围为-127~127，分辨率为0.1m/s，正速度为远离，负速度为靠近目标。例如0xE7是一个负数，等于-25，代表速度为-2.5m/s；30代表3.0m/s。
                         */
                        LOGGER.info("速度:" + ((double) Integer.parseInt(hexString, 16)) / 10 + "m/s");
                    } else if ((i - 8) % 6 == 4) {
                        /**
                         * 无符号数据，范围为0~255，给出的值为计算结果的dB表示，这个只是相对值和实际功率无关。
                         */
                        LOGGER.info("回波功率:" + Integer.parseInt(hexString, 16) + "dB");
                    } else if ((i - 8) % 6 == 5) {
                        xDistance.append(hexString);
                    } else if ((i - 8) % 6 == 0) {
                        xDistance.insert(0, hexString);
                        /**
                         * X轴距离为实际距离单位（米），占两个字节，有符号数据，低字节在前，高字节在后，范围为-23767~32767（为了兼容其它雷达，测量范围比较宽），分辨率为0.1m。以雷达为中心点，左侧的值负，右侧为正。表示的最大距离为-3276.7m到3276.7m。例如十进制123表示12.3m，雷达右侧12.3m。
                         */
                        LOGGER.info("X轴:" + ((double) Integer.parseInt(xDistance.toString(), 16)) / 10 + "m");
                        yDistance.delete(0, yDistance.length());
                        xDistance.delete(0, xDistance.length());
                        current++;
                    }
                }
            }
        }
        LOGGER.info("receive message from server : {}", msg);
    }

    /**
     * 向服务器发送消息
     *
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