package com.joyance.demo.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonProxy implements InvocationHandler{

	IPerson person;
	
	public PersonProxy(IPerson person){
		this.person = person;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy mode");
		return method.invoke(person, args);
	}
	
}
