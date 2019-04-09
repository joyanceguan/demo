package com.joyance.demo.patterns;

public class Factory {

	public interface Animal{
		public String say();
	}
	
	public class Dog implements Animal{

		@Override
		public String say() {
			return "wang wang wang!!!";
		}
		
	}
	
	public class Cat implements Animal{

		@Override
		public String say() {
			return "miao miao miao!!!";
		}
		
	}
	
	public class AnimalFactory{
		public Animal getDog(){
			return new Dog();
		}
		
		public Animal getCat(){
			return new Cat();
		}
	}
	
	public static void main(String[] args) {
		AnimalFactory f = new Factory().new AnimalFactory();
		System.out.println(f.getDog().say());
		System.out.println(f.getCat().say());
	}
}
