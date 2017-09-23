package pl.ene.springplayground.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerServiceExecutionTimer {

	@Pointcut(value="execution(** pl.ene.springplayground.service.CustomerService.payBill(..))")
	public void measurePayBill(){
	}
	
	@Around(value="measurePayBill()")
	private Object measure( ProceedingJoinPoint jp) {
		String methodName = jp.getSignature().getName();
		long beforeTime = System.currentTimeMillis();
		Object ret = null;
		try {
			ret = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long afterTime = System.currentTimeMillis();
		System.out.println( "Execution of " + methodName + " took " + (afterTime - beforeTime) + " miliseconds");
		return ret;
	}
}
