package com.ultra.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.ultra.util.ValidUtil;

/**
 * HibernateValidator错误结果处理切面
 */
@Aspect
@Component
@Order(2)
public class ErrorsAspect {

	@Autowired
	protected MessageSource messageSource;

	@Pointcut("execution(public * com.ultra.web.*.*(..))")
	public void Errors() {
	}

	@Around("Errors()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			if (arg instanceof BindingResult) {
				Errors errors = (Errors) arg;
				if (errors.hasErrors()) {
					throw new IllegalArgumentException(ValidUtil.getIllegalMess(errors, messageSource));
				}
			}
		}
		return joinPoint.proceed();
	}
}
