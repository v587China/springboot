package com.ultra.netty.tcp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 十六进制解码器
 *
 * @author admin
 */
public class HexDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in != null) {
            //创建字节数组,buffer.readableBytes可读字节长度
            byte[] b = new byte[in.readableBytes()];
            in.readBytes(b);
            out.add(bytesToHexString(b));
        }
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte t : bytes) {
            if ((t & 0xF0) == 0) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(t & 0xFF));
            //t & 0xFF 操作是为去除Integer高位多余的符号位（java数据是用补码表示）
        }
        return sb.toString().toUpperCase();
    }
}
