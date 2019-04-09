package com.joyance.demo.spring.aop;

import org.springframework.stereotype.Service;

import com.joyance.demo.spring.MethodAnn;

@Service
public class AnimalServiceImpl{
	
	@MethodAnn(food = "fish")
	public String getName(String name) {
		System.out.println("it is "+name);
		return name;
	}

}
