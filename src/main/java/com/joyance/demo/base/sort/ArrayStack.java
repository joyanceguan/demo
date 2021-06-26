package com.joyance.demo.base.sort;

import java.util.Random;

public class ArrayStack {

	private int index = -1;
	private String[] items = new String[100];
	
	
	public String push(String value){
		items[++index] = value;
		return value;
 	}
	
	public String pop(){
		return items[index--];
	}
	
	public String top(){
		return items[index];
	}
	
	public int size(){
		return index+1;
	}
	
	public boolean isEmpty(){
		return index == -1;
	}

	public static void main(String[] args) {
		Random r = new Random();
		ArrayStack as = new ArrayStack();
		for(int i=0;i<10;i++){
			int v = r.nextInt(100);
			System.out.println(v);
			as.push(""+v);
		}
		
		System.out.println("--------------");
		while(!as.isEmpty()){
			System.out.println(as.pop());
		}
	}
}
