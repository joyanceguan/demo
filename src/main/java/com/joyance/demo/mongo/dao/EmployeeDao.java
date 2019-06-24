package com.joyance.demo.mongo.dao;

import java.util.List;

import com.joyance.demo.mongo.Employee;

public interface EmployeeDao {

	public List<Employee> findAll();
	
	public void insertOneEmployee(Employee employee);
	
	public void deleteOneEmployeeByName(String name);
	
	 public void deleteOneEmployee(String id);
	 
	 public Employee findByName(String name) ;
	 
	 public Employee find(String id) ;
}
