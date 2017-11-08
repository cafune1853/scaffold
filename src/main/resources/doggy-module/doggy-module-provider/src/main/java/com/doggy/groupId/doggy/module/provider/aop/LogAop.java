package com.doggy.groupId.doggy.module.provider.aop;

import com.doggy.groupId.doggy.module.common.interceptor.LogInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(Integer.MIN_VALUE)
@Aspect
@Component
public class LogAop {
	@Pointcut("execution(* com.doggy.groupId.doggy.module.api.service..*.*(..))")
	private void allService(){}
	
	@Around("allService()")
	private Object serviceLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		LogInterceptor serviceLogInterceptor = new LogInterceptor("Access", "");
		return serviceLogInterceptor.around(proceedingJoinPoint);
	}
	
	@Pointcut("execution(* com.doggy.groupId.doggy.module.provider.dao..*.*(..))")
	private void allDao(){}
	
	@Around("allDao()")
	private Object daoLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		LogInterceptor serviceLogInterceptor = new LogInterceptor("Dao", "");
		return serviceLogInterceptor.around(proceedingJoinPoint);
	}
	
	//TODO: add Remote service invoke log
	//@Pointcut("execution(* com.doggy.groupId.doggy.module.api.service..*.*(..))")
	//private void allRemote(){}
}
