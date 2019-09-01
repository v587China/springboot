package com.ultra.netty.tcp.server;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Tcp Server 开关
 *
 * @author admin
 */
public class TcpServerConditional implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String open = conditionContext.getEnvironment().getProperty("netty.tcp.server.open", "false");
        return Boolean.valueOf(open);
    }
}
