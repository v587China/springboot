**TCP/IP协议**

`TCP/IP是个协议组，可分为四个层次：网络接口层、网络层、传输层和应用层。`

   - `网络层有IP协议、ICMP协议、ARP协议、RARP协议和BOOTP协议`
   - `传输层中有TCP协议与UDP协议`
   - `应用层有HTTP,FTP、TELNET、SMTP、DNS等协议`

`socket只是一种连接模式，不是协议，socket是对TCP/IP协议的封装，Socket本身并不是协议，而是一个调用接口`


**Decoders(解码器)**

`ByteToMessageDecoder:把字节解码为消息`
```java
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in,
                       List<Object> out) throws Exception {
        if (in.readableBytes() >= 4) {  // Check if there are at least 4 bytes readable
            out.add(in.readInt());      //Read integer from inbound ByteBuf, add to the List of decodec messages
        }
    }
}
```
`MessageToMessageDecoder:把消息解码为另一种格式的消息`
```java
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {
    @Override
    public void decode(ChannelHandlerContext ctx, Integer msg,
                       List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
```

**Encoders(编码器)**

`MessageToByteEncoder:把消息编码为消息`
```java
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
public class IntegerToByteEncoder extends MessageToByteEncoder<Short> {
    @Override
    public void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out)
            throws Exception {
        out.writeShort(msg);
    }
}
```
`MessageToMessageEncoder:把消息编码为字节`
```java
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {
    @Override
    public void encode(ChannelHandlerContext ctx, Integer msg,
                       List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}
```