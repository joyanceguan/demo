package com.joyance.demo.spring.factorybean;

public class Person {
	
	public Person(){
		System.out.println("person init ...");
	}

	public void sayHello(String name){
		System.out.println(name + " say hello");
	}

}
