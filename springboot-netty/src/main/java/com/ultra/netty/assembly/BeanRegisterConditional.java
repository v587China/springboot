package com.ultra.netty.assembly;

import com.ultra.netty.tcp.client.TcpClient;
import com.ultra.netty.tcp.client.TcpClientChannelHandler;
import com.ultra.netty.tcp.client.TcpClientChannelInitializer;
import com.ultra.netty.tcp.server.TcpServer;
import com.ultra.netty.tcp.server.TcpServerChannelHandler;
import com.ultra.netty.tcp.server.TcpServerChannelInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * 组件注册条件判断
 *
 * @author admin
 */
@Slf4j
public class BeanRegisterConditional implements Condition {
    private static boolean isInit = false;
    private static final List<String> REGISTERED_BEANS = new ArrayList<>();


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if (!isInit) {
            Environment environment = conditionContext.getEnvironment();
            initTcpServer(environment);
            initTcpClient(environment);
            isInit = true;
            log.info("需要注册的组件：{}", REGISTERED_BEANS);
        }
        if (annotatedTypeMetadata instanceof AnnotationMetadata) {
            AnnotationMetadata annotationMetadata = (AnnotationMetadata) annotatedTypeMetadata;
            String className = annotationMetadata.getClassName();
            return REGISTERED_BEANS.contains(className);
        }
        return false;
    }

    private void initTcpClient(Environment environment) {
        boolean tcpClientSwitch = Boolean.parseBoolean(environment.getProperty("tcp.client.switch", "false"));
        if (tcpClientSwitch) {
            REGISTERED_BEANS.add(TcpClient.class.getName());
            REGISTERED_BEANS.add(TcpClientChannelHandler.class.getName());
            REGISTERED_BEANS.add(TcpClientChannelInitializer.class.getName());
        }
    }

    private void initTcpServer(Environment environment) {
        boolean tcpServerSwitch = Boolean.parseBoolean(environment.getProperty("tcp.server.switch", "false"));
        if (tcpServerSwitch) {
            REGISTERED_BEANS.add(TcpServer.class.getName());
            REGISTERED_BEANS.add(TcpServerChannelHandler.class.getName());
            REGISTERED_BEANS.add(TcpServerChannelInitializer.class.getName());
        }
    }
}
