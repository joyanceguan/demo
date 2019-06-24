package com.joyance.demo.base.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

public class ListCollect {

	public static void main(String[] args) {
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
}
