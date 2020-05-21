package com.joyance.demo.base.jdk8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapLambda {

	public static void main(String[] args) {
		 Map<String,Integer> map = new HashMap<String,Integer>();
		 List<Student> list = Arrays.asList(new Student("1-1","joy",80),new Student("1-2","joyance",90),
					new Student("1-3","joyguan",95),new Student("1-1","joyanceguan",85),new Student("1-1","joy",80));
		 list.forEach(student->map.merge(student.getName(), student.getScore(), Integer::sum));
		 System.out.println(map);
	}
}
