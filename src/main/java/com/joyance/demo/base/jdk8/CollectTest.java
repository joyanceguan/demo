package com.joyance.demo.base.jdk8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

public class CollectTest {
	
	public static void main(String[] args) {
		 //
		 List<Student> list = Arrays.asList(new Student("1-1","joy",80),new Student("1-2","joyance",90),
				new Student("1-3","joyguan",95),new Student("1-1","joyanceguan",85));
		 Map<String,Long> result= list.stream().collect(Collectors.groupingBy(Student::getClasz,Collectors.counting()));
	     System.out.println(result);
	     
	     //
	     Map<String, Double> map = list.stream().collect(Collectors.groupingBy(Student::getClasz,Collectors.averagingInt(Student::getScore)));
	     System.out.println(map);
	     
	     //
	     Map<String, Optional<Student>> ScoreMap = list.stream().collect(Collectors.groupingBy(Student::getClasz,Collectors.maxBy(Comparator.comparingInt(Student::getScore))));
	     for(String key:ScoreMap.keySet()){
	    	 Student student = ScoreMap.get(key).get();
	    	 System.out.println(student.getClasz()+"班，最高分是"+student.getScore());
	     }
	     
	     list.forEach(s -> System.out.println(JSON.toJSONString(s)));
	     
	     
	     
	     
	     Map<String, List<Student>> result5 =  list.stream().collect(Collectors.groupingBy(Student::getClasz,Collectors.toList()));
	     System.out.println(JSON.toJSONString(result5));
	     
	     
	}
	
}
