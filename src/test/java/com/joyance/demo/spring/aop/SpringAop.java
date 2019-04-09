package com.joyance.demo.spring.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.joyance.demo.spring.MethodAnn;

@Component
@Aspect
public class SpringAop {

	public final static String ANNOTATION="@annotation(com.joyance.demo.spring.MethodAnn)";
	
	public final static String URL="execution(public * com.joyance.demo.spring.IPersonService.*(..))";
	
	@Around(URL)
	public Object invoke(ProceedingJoinPoint point) throws Throwable{
		Signature signature = point.getSignature();
	    MethodSignature ms = (MethodSignature)signature;
		Method method = ms.getMethod();
		MethodAnn methodAnn=method.getDeclaredAnnotation(MethodAnn.class);
		System.out.println("==++"+methodAnn.food());
		Object[] args = point.getArgs();
		Object result = point.proceed(args);
		return result;
	}
	
}
