package com.ultra.conditional;

import com.ultra.util.StringUtil;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;

import java.util.Arrays;
import java.util.List;

/**
 * 组件注册条件判断
 *
 * @author admin
 */
public class RegisterConditional implements Condition {
    private static boolean needInit = true;
    private static boolean isNotNull;
    private static List<String> falseConditionals;

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if (needInit) {
            Environment environment = conditionContext.getEnvironment();
            String falseConditional = environment.getProperty("conditional.false");
            isNotNull = StringUtil.isNotBlank(falseConditional);
            if (isNotNull) {
                falseConditionals = Arrays.asList(falseConditional.split(";"));
            }
            needInit = false;
        }
        if (isNotNull) {
            if (annotatedTypeMetadata instanceof AnnotationMetadataReadingVisitor) {
                AnnotationMetadataReadingVisitor annotationMetadataReadingVisitor = (AnnotationMetadataReadingVisitor) annotatedTypeMetadata;
                String className = annotationMetadataReadingVisitor.getClassName();
                String simpleName = className.substring(className.lastIndexOf(".") + 1);
                return !falseConditionals.contains(simpleName);
            }
        }
        return true;
    }
}
