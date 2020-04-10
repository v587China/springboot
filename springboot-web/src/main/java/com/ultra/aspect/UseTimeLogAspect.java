package com.ultra.aspect;

import com.ultra.dao.entity.UseTimeLog;
import com.ultra.service.UseTimeLogService;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 各个组件耗时日志处理切面
 *
 * @author admin
 */
@Aspect
@Component
@Order(3)
public class UseTimeLogAspect {

    @Autowired
    private UseTimeLogService useTimeLogService;

    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    private static final Map<Long, List<UseTimeLog>> ID_USE_TIME_LOG = new ConcurrentHashMap<>();

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
        Long baseId = THREAD_LOCAL.get();
        long id = System.currentTimeMillis() * 1000L + new Random().nextInt(1000);
        useTimeLog.setId(id);
        List<UseTimeLog> useTimeLogs;
        if (baseId == null) {
            useTimeLog.setPId(0L);
            useTimeLogs = new ArrayList<>(8);
            THREAD_LOCAL.set(id);
            ID_USE_TIME_LOG.put(id, useTimeLogs);
        } else {
            useTimeLogs = ID_USE_TIME_LOG.get(baseId);
            useTimeLog.setPId(useTimeLogs.get(useTimeLogs.size() - 1).getId());
        }
        useTimeLogs.add(useTimeLog);
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
            StringBuilder args = new StringBuilder();
            for (int i = 0; i < length; i++) {
                args.append(parameters[i].getParameterizedType().getTypeName()).append(" ").append(parameters[i].getName());
            }
            useTimeLog.setParameters(ArrayUtils.toString(args));
        }
        useTimeLog.setReturnType(method.getReturnType().getName());
        useTimeLog.setModifiers(method.getModifiers());
        if (useTimeLog.getPId() == 0) {
            useTimeLogs = ID_USE_TIME_LOG.get(id);
            useTimeLogService.saveBatch(useTimeLogs, useTimeLogs.size());
            THREAD_LOCAL.remove();
        }
        return result;
    }

}
