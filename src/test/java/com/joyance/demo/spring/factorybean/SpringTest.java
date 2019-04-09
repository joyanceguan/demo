package com.joyance.demo.spring.factorybean;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class SpringTest {

	@Resource(name ="person")
	Person person;
	
	@Test
	public void testHelloworld(){
		person.sayHello("joy");
		System.out.println("hello world");
	}
	
	
}
