package com.joyance.demo.spring;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean<T> implements FactoryBean<T>{
	
	Class<T> zclass;
	
	public MyFactoryBean(){
		System.out.println("MyFactoryBean init");
	}
	
	@Override
	public T getObject() throws Exception {
		return MyProxy.createObject(zclass);
	}

	@Override
	public Class<?> getObjectType() {
		return zclass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public Class<T> getZclass() {
		return zclass;
	}

	public void setZclass(Class<T> zclass) {
		this.zclass = zclass;
	}

	
}
