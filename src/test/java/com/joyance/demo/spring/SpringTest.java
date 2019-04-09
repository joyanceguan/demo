package com.joyance.demo.spring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class SpringTest {

	
	@Resource(name="IPersonService")
	IPersonService iPersonService;
	
	@Test
	public void testPerson(){
		Person p = new Person();
		p.setAge(21);
		p.setName("john");
		String result = iPersonService.eat(p);
		System.out.println(result);
	}
}
