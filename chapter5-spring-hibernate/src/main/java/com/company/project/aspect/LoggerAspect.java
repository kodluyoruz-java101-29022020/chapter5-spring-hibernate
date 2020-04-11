package com.company.project.aspect;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.company.project.annotation.enums.LogTimeUnit;


@Aspect
@Component
public class LoggerAspect {

    private static final Logger logger = LogManager.getLogger(LoggerAspect.class);
	
	@Around("@annotation(logger)")
	public Object execute(ProceedingJoinPoint proceedingJoinPoint, com.company.project.annotation.Logger logger) throws Throwable {
		
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();
		
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();
		
		Object result = proceedingJoinPoint.proceed();
		
		stopWatch.stop();
		
		LogTimeUnit timeUnit = logger.timeUnit();
		
		double time = 0;
		
		if(LogTimeUnit.MILLISECOND.equals(timeUnit)) {
			time = stopWatch.getTime(TimeUnit.MILLISECONDS);
		}
		else if(LogTimeUnit.SECOND.equals(timeUnit)) {
			time = stopWatch.getTime(TimeUnit.SECONDS);
		}
		
		LoggerAspect.logger.info(className + "#" + methodName + " executed in " + time + " " + timeUnit.toString());
		
		return result;
	}
	
}
