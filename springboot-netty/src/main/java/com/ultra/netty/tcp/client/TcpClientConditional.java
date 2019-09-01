package com.ultra.netty.tcp.client;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Tcp Server 开关
 *
 * @author admin
 */
public class TcpClientConditional implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String open = conditionContext.getEnvironment().getProperty("tcp.server.open", "false");
        return Boolean.valueOf(open);
    }
}
