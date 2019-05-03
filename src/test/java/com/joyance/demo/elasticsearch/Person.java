package com.joyance.demo.elasticsearch;

import java.util.Date;

import com.joyance.demo.utils.DateUtils;

public class Person {

	private int id;
	private String name;
	private Date create_time;
	private int age;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCreate_time() {
		String time = null;
		if(create_time != null)
		    time = DateUtils.parseStringFromDate(create_time, "yyyy-MM-dd HH:mm:ss.SSS");
		return time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
