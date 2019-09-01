package com.ultra.web;

import com.ultra.excetion.TcpToServerException;
import com.ultra.netty.tcp.client.ClientChannelHandler;
import com.ultra.netty.tcp.server.ServerChannelHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tcp")
public class TcpController {

    @Autowired
    private ClientChannelHandler clientChannelHandler;
    @Autowired
    private ServerChannelHandler serverChannelHandler;

    @GetMapping("/toServer")
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
            serverChannelHandler.sendMsgToTcpServer(msg);
        } catch (Exception e) {
            TcpToServerException tcpToServerException = new TcpToServerException(e.getMessage());
            //可以直接创建new TcpToServerException(e)保留原始异常;
            //initCause是为了自定义信息时,保留原始异常的方式
            tcpToServerException.initCause(e);
            throw tcpToServerException;
        }
    }

}
