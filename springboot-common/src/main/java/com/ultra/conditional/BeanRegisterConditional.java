package com.ultra.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 组件注册条件判断
 *
 * @author admin
 */
public class BeanRegisterConditional implements Condition {
    private static boolean needInitSwitch = true;
    private static final List<String> UNREGISTERED_BEANS = new ArrayList<>();
    /**
     * 因引用包关系，这里定义全类名
     */
    private static final String TCP_SERVER = "com.ultra.netty.tcp.server.TcpServer";
    private static final String TCP_SERVER_CHANNEL_HANDLER = "com.ultra.netty.tcp.server.TcpServerChannelHandler";
    private static final String TCP_SERVER_CHANNEL_INITIALIZER = "com.ultra.netty.tcp.server.TcpServerChannelInitializer";
    private static final String TCP_CLIENT = "com.ultra.netty.tcp.client.TcpClient";
    private static final String TCP_CLIENT_CHANNEL_HANDLER = "com.ultra.netty.tcp.client.TcpClientChannelHandler";
    private static final String TCP_CLIENT_CHANNEL_INITIALIZER = "com.ultra.netty.tcp.client.TcpClientChannelInitializer";
    private static final String LOG_AUDIT_ASPECT = "com.ultra.aspect.LogAuditAspect";
    private static final String CAS_CLIENT = "com.ultra.config.CasClientConfig";
    private static final String LOG_REQUEST_ASPECT = "com.ultra.aspect.RequestLogAspect";
    private static final String LOG_TIME_ASPECT = "com.ultra.aspect.UseTimeLogAspect";


    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if (needInitSwitch) {
            Environment environment = conditionContext.getEnvironment();
            initTcpServer(environment);
            initTcpClient(environment);
            initCasClient(environment);
            initLogAudit(environment);
            initLogRequest(environment);
            initLogTime(environment);
            needInitSwitch = false;
        }
        if (annotatedTypeMetadata instanceof AnnotationMetadataReadingVisitor) {
            AnnotationMetadataReadingVisitor annotationMetadataReadingVisitor = (AnnotationMetadataReadingVisitor) annotatedTypeMetadata;
            String className = annotationMetadataReadingVisitor.getClassName();
            return !UNREGISTERED_BEANS.contains(className);
        }
        return true;
    }

    private void initLogTime(Environment environment) {
        boolean logTimeSwitch = Boolean.parseBoolean(environment.getProperty("log.time.switch", "false"));
        if (!logTimeSwitch) {
            UNREGISTERED_BEANS.add(LOG_TIME_ASPECT);
        }
    }

    private void initLogRequest(Environment environment) {
        boolean logRequestSwitch = Boolean.parseBoolean(environment.getProperty("log.request.switch", "false"));
        if (!logRequestSwitch) {
            UNREGISTERED_BEANS.add(LOG_REQUEST_ASPECT);
        }
    }

    private void initLogAudit(Environment environment) {
        boolean logAuditSwitch = Boolean.parseBoolean(environment.getProperty("log.audit.switch", "false"));
        if (!logAuditSwitch) {
            UNREGISTERED_BEANS.add(LOG_AUDIT_ASPECT);
        }
    }

    private void initCasClient(Environment environment) {
        boolean casClientSwitch = Boolean.parseBoolean(environment.getProperty("cas.client.switch", "false"));
        if (!casClientSwitch) {
            UNREGISTERED_BEANS.add(CAS_CLIENT);
        }
    }

    private void initTcpClient(Environment environment) {
        boolean tcpClientSwitch = Boolean.parseBoolean(environment.getProperty("tcp.client.switch", "false"));
        if (!tcpClientSwitch) {
            UNREGISTERED_BEANS.add(TCP_CLIENT);
            UNREGISTERED_BEANS.add(TCP_CLIENT_CHANNEL_HANDLER);
            UNREGISTERED_BEANS.add(TCP_CLIENT_CHANNEL_INITIALIZER);
        }
    }

    private void initTcpServer(Environment environment) {
        boolean tcpServerSwitch = Boolean.parseBoolean(environment.getProperty("tcp.server.switch", "false"));
        if (!tcpServerSwitch) {
            UNREGISTERED_BEANS.add(TCP_SERVER);
            UNREGISTERED_BEANS.add(TCP_SERVER_CHANNEL_HANDLER);
            UNREGISTERED_BEANS.add(TCP_SERVER_CHANNEL_INITIALIZER);
        }
    }
}
