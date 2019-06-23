package com.joyance.demo.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.joyance.demo.mongo.dao.EmployeeDao;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-mvc.xml") 
public class Base {

	@Autowired
	EmployeeDao employeeDao;
	
	@Test
	public void test_save(){
		Employee employee = new Employee("joy",28);
		employeeDao.insertOneEmployee(employee);
	}
}

