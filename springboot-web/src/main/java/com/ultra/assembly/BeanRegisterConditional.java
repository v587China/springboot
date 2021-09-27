package com.ultra.assembly;

import com.ultra.aspect.LogAuditAspect;
import com.ultra.aspect.RequestLogAspect;
import com.ultra.aspect.UseTimeLogAspect;
import com.ultra.config.CasClientConfig;
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
            initCasClient(environment);
            initLogAudit(environment);
            initLogRequest(environment);
            initLogTime(environment);
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

    private void initLogTime(Environment environment) {
        boolean logTimeSwitch = Boolean.parseBoolean(environment.getProperty("log.time.switch", "false"));
        if (logTimeSwitch) {
            REGISTERED_BEANS.add(UseTimeLogAspect.class.getName());
        }
    }

    private void initLogRequest(Environment environment) {
        boolean logRequestSwitch = Boolean.parseBoolean(environment.getProperty("log.request.switch", "false"));
        if (logRequestSwitch) {
            REGISTERED_BEANS.add(RequestLogAspect.class.getName());
        }
    }

    private void initLogAudit(Environment environment) {
        boolean logAuditSwitch = Boolean.parseBoolean(environment.getProperty("log.audit.switch", "false"));
        if (logAuditSwitch) {
            REGISTERED_BEANS.add(LogAuditAspect.class.getName());
        }
    }

    private void initCasClient(Environment environment) {
        boolean casClientSwitch = Boolean.parseBoolean(environment.getProperty("cas.client.switch", "false"));
        if (casClientSwitch) {
            REGISTERED_BEANS.add(CasClientConfig.class.getName());
        }
    }
}
