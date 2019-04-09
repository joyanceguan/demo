package com.joyance.demo.spring;


@ClassAnn
public interface IPersonService {

	@MethodAnn(food = "apple")
	public String eat(Person p);
	
	@MethodAnn(sleepTime = 9)
	public String sleep(Person p);
}
