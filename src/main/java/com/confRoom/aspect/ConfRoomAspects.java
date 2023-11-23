package com.confRoom.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class ConfRoomAspects {
	
	
	/*
	 * @Before("execution(* com.confRoom.service..*(..))") public void
	 * printLogs(JoinPoint joinpoint) { System.out.print("Method invoked:  ");
	 * System.out.print(joinpoint.getSignature().getName()); }
	 */
	@Pointcut(
		    value
		    = "execution(* com.confRoom.service..*(..))")
		private void
		printLogs()
		{
		}
		 
		
		@Around(value = "printLogs()")
		public Object
		logsAroundAdvice(ProceedingJoinPoint proJoinPoint)
		    throws Throwable
		{
			Object obj;
		    System.out.println(
		        "Method Invoked: "
		        + proJoinPoint.getSignature().getName()
		        + " method");
		    try {
		        obj=proJoinPoint.proceed();
		    }
		    finally {
		    }
		    System.out.println(
		        "Method Exit: "
		        + proJoinPoint.getSignature().getName()
		        + " method");
		    return obj;
		}
}
