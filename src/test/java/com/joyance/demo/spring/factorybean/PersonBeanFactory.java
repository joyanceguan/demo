package com.joyance.demo.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

@Service("person")
public class PersonBeanFactory implements FactoryBean<Person>{

	@Override
	public Person getObject() throws Exception {
		Person p = new Person();
		return p;
	}

	@Override
	public Class<?> getObjectType() {
		return Person.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
