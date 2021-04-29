package com.ultra.aspect;

import com.ultra.annotation.LogAudit;
import com.ultra.bo.LogDetails;
import com.ultra.conditional.BeanRegisterConditional;
import com.ultra.constant.LogModuleEnum;
import com.ultra.constant.LogOperateEnum;
import com.ultra.util.ArrayUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志审计切面
 *
 * @author fan
 */
@Aspect
@Component
@Conditional(BeanRegisterConditional.class)
public class LogAuditAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAuditAspect.class);

    /**
     * 使用该注解的地方作为切点
     */
    @Pointcut("@annotation(com.ultra.annotation.LogAudit)")
    public void logAuditPointcut() {
    }

    /**
     * 获取注解参数当做方法入参
     *
     * @param joinPoint 切点方法
     * @param logAudit  注解参数
     * @return 方法执行的返回值
     * @throws Throwable 方法执行可能抛的异常
     */
    @Around("@annotation(logAudit)")
    public Object doAround(ProceedingJoinPoint joinPoint, LogAudit logAudit) throws Throwable {
        Object proceed = null;
        LogDetails logDetails = new LogDetails();
        try {
            // 伪代码实现获取用户
            String account = "admin";
            String operateId = logAudit.operateId();
            String moduleId = logAudit.moduleId();
            String id = getElValue(logAudit.id(), joinPoint);
            String name = getElValue(logAudit.name(), joinPoint);
            logDetails.setAccount(account);
            logDetails.setOperate(LogOperateEnum.getValue(operateId));
            logDetails.setModule(LogModuleEnum.getValue(moduleId));
            logDetails.setId(id);
            logDetails.setName(name);
            proceed = joinPoint.proceed();
            // 这里假定认为没有异常是成功，有异常是失败；根据实际业务判断
            logDetails.setResult("成功");
        } catch (Throwable throwable) {
            logDetails.setResult("失败");
            throw throwable;
        } finally {
            logger.info("logDetails:{}", logDetails);
        }
        return proceed;
    }

    /**
     * 用于SpEL表达式解析.
     */
    private SpelExpressionParser parser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    private String getElValue(String elKey, ProceedingJoinPoint joinPoint) {
        // 通过joinPoint获取被注解方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 使用spring的DefaultParameterNameDiscoverer获取方法形参名数组
        String[] paramNames = nameDiscoverer.getParameterNames(method);
        if (ArrayUtil.isNotEmpty(paramNames)) {
            // spring的表达式上下文对象
            EvaluationContext context = new StandardEvaluationContext();
            // 通过joinPoint获取被注解方法的形参
            Object[] args = joinPoint.getArgs();
            // 给上下文赋值
            for (int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
            // 解析过后的Spring表达式对象
            Expression expression = parser.parseExpression(elKey);
            Object expressionValue = expression.getValue(context);
            if (expressionValue == null) {
                return null;
            }
            return String.valueOf(expressionValue);
        }
        return null;
    }

}
