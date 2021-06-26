package com.joyance.demo.base.sort;

import java.util.Random;

public class ArrayQueue {

	private int head = 0;
	private int tail = 0;
	
	private String[] items = new String[100];
	
	public void enqueue(String value){
		items[tail++] = value;
	}
	
	public String dequeue(){
		if(head >= tail){
			return null;
		}
		return items[head++];
	} 
	
	public String head(){
		return items[head];
	}
	
	public int size(){
		return tail - head;
	}
	
	public void clear(){
		head = 0;
		tail = 0;
	}
	
	public boolean isEmpty(){
		return head == tail;
	}
	
	public String tail(){
		return items[tail];
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		ArrayQueue aq = new ArrayQueue();
		for(int i=0;i<10;i++){
			int v = r.nextInt(100);
			System.out.println(v);
			aq.enqueue(""+v);
		}
		System.out.println("==============="+aq.size());
		while(!aq.isEmpty()){
			System.out.println(aq.dequeue()+"======"+aq.head());
		}
	}
}
