package com.ultra.netty.tcp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class HexEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (msg != null && msg instanceof String) {
            String hexStr = (String) msg;
            if (hexStr.length() < 1) {
                return;
            }
            int byteLen = hexStr.length() / 2;
            byte[] result = new byte[byteLen];
            char[] hexChar = hexStr.toCharArray();
            for (int i = 0; i < byteLen; i++) {
                result[i] = (byte) (Character.digit(hexChar[i * 2], 16) << 4 | Character.digit(hexChar[i * 2 + 1], 16));
            }
            out.writeBytes(result);
        }
    }
}
