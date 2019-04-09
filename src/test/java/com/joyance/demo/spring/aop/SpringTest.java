package com.joyance.demo.spring.aop;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joyance.demo.spring.IPersonService;
import com.joyance.demo.spring.Person;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class SpringTest {

	@Resource
	AnimalServiceImpl animalService;
	
	@Resource(name="IPersonService")
	IPersonService iPersonService;
	
	@Test
	public void testHelloworld(){
		Person p = new Person();
		p.setAge(21);
		p.setName("john");
		String result = iPersonService.eat(p);
		System.out.println(result);
		
//		animalService.getName("cookie");
	}
	
	
}
