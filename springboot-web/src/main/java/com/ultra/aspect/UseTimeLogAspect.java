package com.ultra.aspect;

import com.ultra.assembly.BeanRegisterConditional;
import com.ultra.entity.UseTimeLog;
import com.ultra.service.UseTimeLogService;
import com.ultra.service.impl.UseTimeLogServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
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
@Conditional(BeanRegisterConditional.class)
public class UseTimeLogAspect {

    /**
     * 忽略的方法
     */
    private static final List<String> IGNORE_METHOD = Collections.singletonList("saveBatch");
    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();
    private static final Map<Long, List<UseTimeLog>> ID_USE_TIME_LOG = new ConcurrentHashMap<>();
    @Autowired
    private UseTimeLogService useTimeLogService;

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
        Object target = joinPoint.getTarget();
        Class<?> clazz = target.getClass();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        if (clazz == UseTimeLogServiceImpl.class && IGNORE_METHOD.contains(methodName)) {
            return joinPoint.proceed();
        }
        UseTimeLog useTimeLog = new UseTimeLog();
        Long baseId = THREAD_LOCAL.get();
        long id = System.currentTimeMillis() * 1000 + new Random().nextInt(1000);
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
        if (AopUtils.isAopProxy(clazz) || Proxy.isProxyClass(clazz)) {
            String name;
            Type[] types = clazz.getGenericInterfaces();
            name = types[0].getTypeName();
            useTimeLog.setClassName(name);
        } else {
            useTimeLog.setClassName(clazz.getName());
        }
        useTimeLog.setMethodName(methodName);
        Parameter[] parameters = method.getParameters();
        if (ArrayUtils.isNotEmpty(parameters)) {
            StringBuilder args = new StringBuilder();
            for (Parameter parameter : parameters) {
                args.append(parameter.getParameterizedType().getTypeName()).append(" ").append(parameter.getName());
            }
            useTimeLog.setParameters(ArrayUtils.toString(args));
        }
        useTimeLog.setReturnType(method.getReturnType().getName());
        useTimeLog.setModifiers(method.getModifiers());
        if (useTimeLog.getPId() == 0) {
            useTimeLogs = ID_USE_TIME_LOG.get(id);
            useTimeLogService.saveBatch(useTimeLogs, useTimeLogs.size());
            THREAD_LOCAL.remove();
            ID_USE_TIME_LOG.remove(id);
        }
        return result;
    }

}
