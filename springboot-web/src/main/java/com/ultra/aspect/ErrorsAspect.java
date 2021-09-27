package com.ultra.aspect;

import com.ultra.util.ValidUtil;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * HibernateValidator错误结果处理切面
 *
 * @author admin
 */
@Aspect
@Component
@Order(1)
public class ErrorsAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorsAspect.class);

    @Autowired
    protected MessageSource messageSource;

    @Pointcut("execution(public * com.ultra.web.*.*(..))")
    public void Errors() {
    }

    @Around("Errors()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isNotEmpty(args)) {
            Object arg = args[args.length - 1];
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;
                if (errors.hasErrors()) {
                    String illegalMess = ValidUtil.getIllegalMess(errors, messageSource);
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug(illegalMess);
                    }
                    throw new IllegalArgumentException(illegalMess);
                }
            }
        }
        return joinPoint.proceed();
    }
}
