package com.joyance.demo.base.sort;

import java.util.Random;

/**
 * 两个队列实现一个栈
 */
public class Queue2 {
	
	private ArrayQueue queue1 = new ArrayQueue();
	
	private ArrayQueue queue2 = new ArrayQueue();

	public void push(String item){
		getQueue().enqueue(item);
	}
	
	public String pop(){
		if(isEmpty()){
			return null;
		}
		if(queue2.isEmpty()){
			while(queue1.size()!=1){
				queue2.enqueue(queue1.dequeue());
			}
			String top = queue1.dequeue();
			return top;
		}else{
			while(queue2.size()!=1){
				queue1.enqueue(queue2.dequeue());
			}
			String top = queue2.dequeue();
			return top;
		}
		
	}
	
    public String top(){
    	if(queue2.isEmpty()){
			while(queue1.size()!=1){
				queue2.enqueue(queue1.dequeue());
			}
			String top = queue1.dequeue();
			queue2.enqueue(top);
			return top;
		}else{
			while(queue2.size()!=1){
				queue1.enqueue(queue2.dequeue());
			}
			String top = queue2.dequeue();
			queue1.enqueue(top);
			return top;
		}
	}
    
    public boolean isEmpty(){
    	return getQueue().isEmpty();
    }
    
    public int size(){
    	return getQueue().size();
    }
    
    private ArrayQueue getQueue(){
    	if(queue2.isEmpty()){
    		return queue1;
    	}
    	return queue2;
    }
    
    public static void main(String[] args) {
    	Random r = new Random();
    	Queue2 as = new Queue2();
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
