package com.infosys.irs.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	
	@AfterThrowing(pointcut = "execution(* com.infosys.ars.repository.*.*(..))", throwing = "exception")
	public void logExceptionFromRepository(Exception exception) {		
		log(exception);		
	}
	
	@AfterThrowing(pointcut = "execution(* com.infosys.ars.service.*.*(..))", throwing = "exception")
	public void logExceptionFromService(Exception exception) {		
		if (exception.getMessage().contains("Service")) {
			log(exception);
		}	
	}	
	
	private void log(Exception exception) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
	}
}
