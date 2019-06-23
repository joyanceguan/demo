package com.joyance.demo.mongo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.joyance.demo.mongo.dao.EmployeeDao;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-mvc.xml") 
public class Base {

	@Autowired
	EmployeeDao employeeDao;
	
	@Test
	public void test_save(){
		Employee employee = new Employee("joyance",29);
		employeeDao.insertOneEmployee(employee);
	}
	
	@Test
	public void test_find(){
		List<Employee> list = employeeDao.findAll();	
		System.out.println(JSON.toJSONString(list));
	}
	
	@Test
	public void test_findByName(){
		Employee employee = employeeDao.findByName("joy");	
		System.out.println(JSON.toJSONString(employee));
	}
	
	@Test
	public void test_deleteOneEmployee(){
		employeeDao.deleteOneEmployee("5d0f997459e0a1ab2c26d14c");
	}
}

