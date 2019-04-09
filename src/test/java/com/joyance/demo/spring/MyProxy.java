package com.joyance.demo.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.alibaba.fastjson.JSON;

public class MyProxy implements InvocationHandler{
	

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String actionName = method.getName();
		MethodAnn methodAnn = method.getAnnotation(MethodAnn.class);
		String x = null;
		if("eat".equals(actionName)){
			x = methodAnn.food();
		}else if("sleep".equals(actionName)){
			x = methodAnn.sleepTime()+"";
		}
		String result = JSON.toJSONString(args) + " " +actionName+ " "+x;
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T createObject(Class<T> classz){
		ClassLoader classLoader = classz.getClassLoader();
		Class<?>[] interfaces = new Class[] { classz };
		MyProxy myProxy = new MyProxy();
		T t = (T) Proxy.newProxyInstance(classLoader, interfaces, myProxy);
		return t;
	}
}
