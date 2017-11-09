package com.doggy.groupId.doggy.module.web.aop;


import com.doggy.groupId.doggy.module.common.interceptor.LogInterceptor;
import com.doggy.groupId.doggy.module.web.annotation.MvcLoad;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@MvcLoad
@Aspect
@Component
@Order(Integer.MIN_VALUE)
public class ControllerLogAop {
	@Pointcut("execution(public * com.doggy.groupId.doggy.module.web.controller..*.*(..))")
	private void allController() {
	}
	
	@Around("allController()")
	private Object controllerLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		LogInterceptor serviceLogInterceptor = new LogInterceptor("Access", "");
		return serviceLogInterceptor.around(proceedingJoinPoint);
	}
}
