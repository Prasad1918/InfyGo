package com.infosys.irs.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.infosys.irs.exception.InfyGoBootException;

@Component
@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.infosys.irs.repository.*.*(..))", throwing = "exception")
	public void logExceptionFromRepository(InfyGoBootException exception) throws InfyGoBootException {
		log(exception);
	}

	@AfterThrowing(pointcut = "execution(* com.infosys.irs.service.*.*(..))", throwing = "exception")
	public void logExceptionFromService(InfyGoBootException exception) throws InfyGoBootException {
		if (exception.getMessage().contains("Service")) {
			log(exception);
		}
	}

	private void log(Exception exception) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
	}
}
