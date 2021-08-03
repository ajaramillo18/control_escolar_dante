/**
 * 
 */
package jama.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author ajara
 *
 */
@Component
@Aspect
public class LoggerAspect {
	
	//TODO see if it is convenient to implement Log4J framework instead of this basic Logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Before("within(jama.*.*)")// to execute for all methods of all classes of controller package
	public void logBeginController(JoinPoint joinPoint)
	{
		
		

		String method = joinPoint.getSignature().toShortString();
		logger.info("---> Calling method: "+ method);	
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			logger.info("---> Calling Arguments:"+arg);
		}
		
	}

	@AfterReturning(pointcut = "within(jama.controller.*)", returning = "objectReturned")//this will be executed  only after the methods returns (as in there are no exceptions)
	public void logSuccessController(JoinPoint joinPoint, Object objectReturned)
	{
		String method = joinPoint.getSignature().toShortString();
		logger.info("---> returning from method: "+ method);
		if(objectReturned!=null)
			logger.info("--->object returned: " + objectReturned.toString());
		
	}

	
	@AfterThrowing(pointcut = "within(jama.*.*)", throwing = "ex")//this will be executed after an exeption is thrown
	public void logErrorController(JoinPoint joinPoint, Exception ex)
	{
		String method = joinPoint.getSignature().toShortString();
		logger.info("---> ERROR in method: "+ method);
		logger.info("exception thrown: " + ex.getMessage());
		logger.info("strack trace: " + ex.getStackTrace());
		
		//TODO add feature to send an email to development team each time an exception occurs
		
	}
	
	
	
}
