package com.joyance.demo.base.sort;

import java.util.ArrayList;
import java.util.List;

public class Queue1 {
	
	List<Integer> list = new ArrayList<Integer>();
	
	//入队
	public void enquene(int num){
		list.add(num);
	}
	
	//出队
    public Integer dequene(){
    	if(!isEmpty()){
    		return list.remove(0);
    	}
    	return null;
	}
    
    //头元素
    public int head(){
    	return list.get(0);
    }
    
    //是否为空
    public boolean isEmpty(){
    	return list.isEmpty();
    }
     
    public static void main(String[] args) {
    	Queue1 queue = new Queue1();
    	for(int i=1;i<10;i++){
    		queue.enquene(i);
    	}
    	
    	int index = 0;
    	while(!queue.isEmpty()){
    		 int v = queue.dequene();
    		 if(++index != 3){
    			 queue.enquene(v);
    		 }else{
    			 index = 0;
    			 System.out.println(v);
    		 }
    	}
	}
}
