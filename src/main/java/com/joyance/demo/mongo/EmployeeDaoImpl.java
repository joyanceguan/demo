package com.joyance.demo.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.joyance.demo.mongo.dao.EmployeeDao;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{

	@Autowired
	     private MongoTemplate mongoTemplate;
	 
	     @Override
	     public List<Employee> findAll() {
	         return mongoTemplate.findAll(Employee.class);
	     }
	 
	     @Override
	     public void insertOneEmployee(Employee employee) {
	         mongoTemplate.insert(employee);
	     }
	 
	     @Override
	     public void deleteOneEmployeeByName(String name) {
	         Criteria c = new Criteria();
	         c.and("name").is(name);
	         Query query = new Query(c);
	         mongoTemplate.remove(query, Employee.class);
	     }
	 
	     @Override
	     public void deleteOneEmployee(String id) {
	         Criteria c = new Criteria();
	         c.and("_id").is(id);
	         Query query = new Query(c);
	         mongoTemplate.remove(query, Employee.class);
	     }
	 
	     @Override
	     public Employee findByName(String name) {
	         Criteria c = new Criteria();
	         c.and("name").is(name);
	         Query query = new Query(c);
	         return mongoTemplate.findOne(query, Employee.class);
	     }
	 
	     @Override
	     public Employee find(String id) {
	         return mongoTemplate.findById(id, Employee.class);
	     }
}
