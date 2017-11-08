package com.doggy.groupId.doggy.module.common.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * 这个AOP的优先级应该低于异常转型，否则永远无法捕捉到异常日志
 */
public class LogInterceptor {
	private static final String DEFAULT_LOG_NAME = "common-logger";
	private final Logger log;
	private final String logName;
	private String[] methodPrefixes;
	
	public LogInterceptor(String logName, String methodPrefix) {
		if (!isEmpty(logName)) {
			this.logName = logName;
		}else{
			this.logName = DEFAULT_LOG_NAME;
		}
		this.log = LoggerFactory.getLogger(logName);
		if (!isEmpty(logName)) {
			methodPrefixes = methodPrefix.split(",");
		}
	}
	
	public Object around(ProceedingJoinPoint point) throws Throwable {
		String methodName = point.getSignature().getName();
		String className = point.getTarget().getClass().getSimpleName();
		Object[] args = point.getArgs();
		if (methodPrefixes != null) {
			boolean match = false;
			for (String str : methodPrefixes) {
				if (methodName.startsWith(str)) {
					match = true;
					break;
				}
			}
			if (!match) {
				return point.proceed();
			}
		}
		StopWatch totalStopWatch = new StopWatch();
		totalStopWatch.start();
		Object result = null;
		try {
			result = point.proceed();
			totalStopWatch.stop();
			if(log.isInfoEnabled()){
				log.info("[{}]{}-{}, cost:{} , args:{}, result:{}", logName, className, methodName, totalStopWatch.getTotalTimeMillis(), objectArrayToString(args), result);
			}
		} catch (Throwable e) {
			totalStopWatch.stop();
			if(log.isErrorEnabled()){
				log.error("[{}]{}-{}, cost:{} , args:{}, exception:", logName, className, methodName, totalStopWatch.getTotalTimeMillis(), objectArrayToString(point.getArgs()), e);
			}
			throw e;
		}
		return result;
	}
	
	private static boolean isEmpty(String str){
		return str == null || "".equals(str.trim());
	}
	
	private static String objectArrayToString(Object[] objArray){
		return Arrays.deepToString(objArray);
	}
}
