package com.ultra.aspect;

import com.ultra.bo.UseTimeLog;
import com.ultra.util.ThreadLocalUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 各个组件耗时日志处理切面
 *
 * @author admin
 */
@Aspect
@Component
@Order(3)
public class UseTimeLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(UseTimeLogAspect.class);

    @Pointcut("execution(public * com.ultra.web.*.*(..))")
    public void webLog() {
    }

    @Pointcut("execution(public * com.ultra.service.impl.*.*(..))")
    public void serviceLog() {
    }

    @Pointcut("execution(public * com.baomidou.mybatisplus.extension.service.impl.*.*(..))")
    public void baseServiceLog() {
    }

    @Pointcut("execution(public * com.ultra.service.impl.*.*(..))")
    public void mapperLog() {
    }

    @Pointcut("execution(public * com.baomidou.mybatisplus.core.mapper.*.*(..))")
    public void baseMapperLog() {
    }

    @Around("webLog()")
    public Object doWebAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return recordUseTime(joinPoint);
    }

    @Around("serviceLog()")
    public Object doServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return recordUseTime(joinPoint);
    }

    @Around("baseServiceLog()")
    public Object doBaseServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return recordUseTime(joinPoint);
    }

    @Around("mapperLog()")
    public Object doMapperAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return recordUseTime(joinPoint);
    }

    @Around("baseMapperLog()")
    public Object doBaseMapperAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return recordUseTime(joinPoint);
    }


    /**
     * 统计耗时
     *
     * @param joinPoint 切面
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    private Object recordUseTime(ProceedingJoinPoint joinPoint) throws Throwable {
        UseTimeLog useTimeLog = new UseTimeLog();
        useTimeLog.setId(System.currentTimeMillis() * 1000 + new Random(1000).nextLong());
        List<UseTimeLog> useTimeLogs = ThreadLocalUtil.getUseTimeLogs();
        if (useTimeLogs == null) {
            useTimeLog.setPId(0L);
        } else {
            useTimeLog.setPId(useTimeLogs.get(useTimeLogs.size() - 1).getId());
        }
        ThreadLocalUtil.addUseTimeLogs(useTimeLog);
        long startTime = System.currentTimeMillis();
        useTimeLog.setStartTime(new Date());
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        useTimeLog.setUseTime((int) (endTime - startTime));
        Object target = joinPoint.getTarget();
        Class<?> clazz = target.getClass();
        if (AopUtils.isAopProxy(clazz) || Proxy.isProxyClass(clazz)) {
            String name;
            Type[] types = clazz.getGenericInterfaces();
            name = types[0].getTypeName();
            useTimeLog.setClassName(name);
        } else {
            useTimeLog.setClassName(clazz.getName());
        }
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        useTimeLog.setMethodName(method.getName());
        Parameter[] parameters = method.getParameters();
        if (ArrayUtils.isNotEmpty(parameters)) {
            int length = parameters.length;
            String[] args = new String[length];
            for (int i = 0; i < length; i++) {
                args[i] = parameters[i].getParameterizedType().getTypeName() + " " + parameters[i].getName();
            }
            useTimeLog.setParameters(args);
        }
        useTimeLog.setReturnType(method.getReturnType().getName());
        useTimeLog.setModifiers(method.getModifiers());
        if (useTimeLog.getPId() == 0) {
            useTimeLogs = ThreadLocalUtil.getUseTimeLogs();
            System.out.println(useTimeLogs);
            ThreadLocalUtil.delete();
            useTimeLogs = ThreadLocalUtil.getUseTimeLogs();
            System.out.println(useTimeLogs);
        }
        return result;
    }

}
