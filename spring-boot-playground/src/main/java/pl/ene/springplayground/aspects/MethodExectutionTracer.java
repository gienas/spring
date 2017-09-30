package pl.ene.springplayground.aspects;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodExectutionTracer {

	@Pointcut(value = "execution(public * pl.ene.springplayground.controller..*(..)) ||"
			+ "execution(public * pl.ene.springplayground.service..*(..))")
	public void showParams() {
	}

	@Around(value = "showParams()")
	private Object measure(ProceedingJoinPoint jp) {
		String packageName = jp.getSignature().getDeclaringTypeName();
		String methodName = jp.getSignature().getName();
		Object[] tab = jp.getArgs();
		long beforeTime = System.currentTimeMillis();
		Object ret = null;
		try {
			ret = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		long afterTime = System.currentTimeMillis();
		System.out.println("Execution of " + packageName + "." + methodName + " took " + (afterTime - beforeTime)
				+ " miliseconds, input params: " + buildInputParamString(tab));
		return ret;
	}
	
	public static String buildInputParamString(Object[] tab) {
		if (tab == null || tab.length == 0) return "[]";
		List<Object> list =  Arrays.asList(tab);
		return "["  + list.stream().map(n -> n==null ? "null" : n.toString()).collect(Collectors.joining(", ")) + "]";
		
	}
	
}
