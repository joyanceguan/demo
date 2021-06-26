package com.joyance.demo.base.sort;

import java.util.Random;

public class ArrayStackT<T> {
	
	private int index = -1;
	private Object[] items = new Object[100];
	
	
	public T push(T value){
		items[++index] = value;
		return value;
 	}
	
	@SuppressWarnings("unchecked")
	public T pop(){
		return (T) items[index--];
	}
	
	@SuppressWarnings("unchecked")
	public T top(){
		return (T) items[index];
	}
	
	public int size(){
		return index+1;
	}
	
	public boolean isEmpty(){
		return index == -1;
	}

	public static void main(String[] args) {
		Random r = new Random();
		ArrayStackT<String> as = new ArrayStackT<String>();
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
