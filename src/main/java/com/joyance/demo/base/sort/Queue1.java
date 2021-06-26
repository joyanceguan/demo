package com.joyance.demo.base.sort;

import com.alibaba.fastjson.JSON;

/**
 * 约瑟夫环
 */
public class Queue1 {
	
	public void invoke(int[] array){
		ArrayQueue queue = new ArrayQueue();
		for(int x:array){
			queue.enqueue(x+"");
		}
			for(int i=1;i<4;i++){
				if(i==3){
					System.out.println(queue.dequeue());
					i=0;
				}else{
					queue.enqueue(queue.dequeue());
				}
				if(queue.isEmpty()){
					break;
				}
		}
	}
	
	public static void main(String[] args) {
		Queue1 queue = new Queue1();
		int[] array = new int[5];
		for(int i=1;i<6;i++){
			array[i-1] = i;
		}
		System.out.println(JSON.toJSONString(array));
		System.out.println("==========");
		queue.invoke(array);
	}
}
