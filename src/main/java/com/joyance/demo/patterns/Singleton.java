package com.joyance.demo.patterns;


public class Singleton {

	private Singleton(){
	}
	
	private static class Inner{
	   private static Singleton singleton = new Singleton();
	}
	
	public static Singleton getInstance(){
		return Inner.singleton;
	}
	
	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton1);
		System.out.println(singleton2);
	}
}
