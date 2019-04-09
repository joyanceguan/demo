package com.joyance.demo.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


public class DynamicProxy {

	
	public static void main(String[] args) {
		IPerson p =new Person();
		InvocationHandler i = new PersonProxy(p);
		Class<?>[] interfaces = new Class[] { IPerson.class };
		IPerson proxy = (IPerson) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), interfaces, i);
	    System.out.println(proxy.eat("apple"));
	}
	
}
