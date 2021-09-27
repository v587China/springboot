package com.ultra.api;

import com.ultra.excetion.TcpToServerException;
import com.ultra.netty.assembly.BeanRegisterConditional;
import com.ultra.netty.tcp.client.TcpClientChannelHandler;
import com.ultra.netty.tcp.server.TcpServerChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
@RequestMapping("/tcp")
public class TcpController {

    @Autowired(required = false)
    private TcpClientChannelHandler clientChannelHandler;
    @Autowired(required = false)
    private TcpServerChannelHandler serverChannelHandler;

    @GetMapping("/toServer")
    @Conditional(value = BeanRegisterConditional.class)
    public void sendMsgToServer(String msg) {
        try {
            clientChannelHandler.sendMsgToTcpServer(msg);
        } catch (Exception e) {
            TcpToServerException tcpToServerException = new TcpToServerException(e.getMessage());
            //可以直接创建new TcpToServerException(e)保留原始异常;
            //initCause是为了自定义信息时,保留原始异常的方式
            tcpToServerException.initCause(e);
            throw tcpToServerException;
        }
    }

    @GetMapping("/toClient")
    public void sendMsgToClient(String msg) {
        try {
            serverChannelHandler.sendMsgToTcpClient(msg);
        } catch (Exception e) {
            TcpToServerException tcpToServerException = new TcpToServerException(e.getMessage());
            //可以直接创建new TcpToServerException(e)保留原始异常;
            //initCause是为了自定义信息时,保留原始异常的方式
            tcpToServerException.initCause(e);
            throw tcpToServerException;
        }
    }

}
