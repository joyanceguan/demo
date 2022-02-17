package com.joyance.demo.base.jdk8;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


public class OptionalTest {
	
	public static void orElseGet(){
		Set<String> set = null;
		set = Optional.ofNullable(set).orElseGet(() -> new HashSet<String>());
		System.out.println("orElseGet result:"+set);
	}
	
	public static void orElse(){
		Set<String> set = new HashSet<String>();
		set.add("a");
		set = Optional.ofNullable(set).orElse(new HashSet<String>());
		System.out.println("orElse result:"+set);
	}
	
	public static void map(){
		Set<String> set = new HashSet<String>();
		set.add("1");
		set.add("2");
		int size = Optional.ofNullable(set).map(s -> s.size()).orElse(0);
		System.out.println("1 map result:"+size);
		
		set = null;
	    size = Optional.ofNullable(set).map(s -> s.size()).orElse(0);
		System.out.println("1 map result:"+size);
	}
	
	public static void isPresent(){
		Set<String> set = null;
		boolean x = Optional.ofNullable(set).isPresent();
		System.out.println("1 isPresent result:"+x);
		
		set = new HashSet<String>();
		set.add("1");
		set.add("2");
		x = Optional.ofNullable(set).isPresent();
		System.out.println("2 isPresent result:"+x);
	}
	
	public static void ifPresent(){
		Set<String> set = new HashSet<String>();
		Optional.ofNullable(set).ifPresent(s->s.add("c"));
		System.out.println("ifPresent result:"+set);
	}
	
	public static void filter(){
		Set<String> set = new HashSet<String>();
		set.add("x1");
		set.add("y1");
		set = Optional.ofNullable(set).filter(s -> s.contains("x0")).orElseGet(()-> new HashSet<String>());
		System.out.println("filter result:"+set);
	}
	
	public static void main(String[] args) {
		OptionalTest.orElseGet();
		OptionalTest.orElse();
		OptionalTest.map();
		OptionalTest.isPresent();
		OptionalTest.ifPresent();
		OptionalTest.filter();
	}
}
