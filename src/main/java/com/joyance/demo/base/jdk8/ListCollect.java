package com.joyance.demo.base.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

public class ListCollect {
	
	public static void collect(){
		List<Student> list = new ArrayList<Student>();
		Student student1 = new Student("1","joy",100);
		Student student2 = new Student("2","joy2",80);
		Student student3 = new Student("3","joy3",75);
		list.add(student1);
		list.add(student2);
		list.add(student3);
		List<Integer> userIdList = list.stream().map(a -> a.getScore()).collect(Collectors.toList());
		System.out.println(JSON.toJSONString(userIdList));
	}
	
	public static void method1(){
		List<String> configList  = new ArrayList<String>();
		configList.add("a");
		String[] configurations = new String[]{"a","b"};
		Arrays.stream(configurations).filter(config -> !configList.contains(config)).forEach(configList::add);
	    System.out.println(configList);
	}

	public static void main(String[] args) {
		ListCollect.collect();
		ListCollect.method1();
		
	}
}
