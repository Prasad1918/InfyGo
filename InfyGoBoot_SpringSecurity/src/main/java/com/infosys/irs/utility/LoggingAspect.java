package com.infosys.irs.utility;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.infosys.irs.exception.InfyGoBootException;



/**
 * The Class LoggingAspect.
 */

@Component
@Aspect
public class LoggingAspect {

	private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	/**
	 * Log around all methods.
	 *
	 * @param joinPoint the join point
	 * @return the object
	 * @throws Throwable the throwable
	 */
	@Around("execution(* com.infosys.irs.controller.*.*(..))")
	public Object logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		String datum=Arrays.toString(joinPoint.getArgs());
		logger.info(className);
		logger.info(methodName);
		logger.info( "Entering in to--> ");
		logger.info( methodName);
		logger.info(" with param--- ");
		logger.info(datum);
		

		Object result = joinPoint.proceed();
		String objectAsString = result.toString();

		long endTime = System.currentTimeMillis();
		String data = Long.toString(endTime - startTime);
		logger.info(className);
		logger.info(methodName);
		logger.info("Exiting ");
		logger.info(methodName);
		logger.info(" with result ");
		logger.info(objectAsString);
		logger.info("--- execution completed in ");
		logger.info(data);
		logger.info(" ms ");

		return result;
	}

	/**
	 * Log db exceptions.
	 *
	 * @param joinPoint the join point
	 * @param exception exception object
	 * @return none
	 */
	@AfterThrowing(pointcut = "execution (* com.infosys.irs.controller.*.*(..))", throwing = "exception")
	public void logRepositoryExceptions(JoinPoint joinPoint, Exception exception)
			throws InfyGoBootException, Exception {
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();
		logger.error("Error and Exception:/n");
		logger.error(className);
		logger.error(methodName);
		logger.error(exception.getMessage());

		throw exception;
	}

	@AfterThrowing(pointcut = "execution (* com.infosys.irs.utility.*.*(..))", throwing = "exception")
	public void logUtilityExceptions(JoinPoint joinPoint, Exception exception) throws InfyGoBootException, Exception {
		String classNameval = joinPoint.getSignature().getDeclaringTypeName();
		String methodNameVal = joinPoint.getSignature().getName();

		logger.error("Error and Exception:");
		logger.error(classNameval);
		logger.error(methodNameVal);
		logger.error(exception.getMessage());

		throw exception;
	}

}