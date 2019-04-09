package com.joyance.demo.elasticsearch;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class EsTest {

	@Autowired
	ESBase esBase;
	
	@Test
	public void testSave(){
		Person p = new Person();
		p.setId(12);
		p.setName("joyance");
		p.setAge(28);
		p.setCtime(new Date());
		String id = esBase.insert("helloworld_index2", "helloworld_type2", p);
		System.out.println(id);
	}
}
