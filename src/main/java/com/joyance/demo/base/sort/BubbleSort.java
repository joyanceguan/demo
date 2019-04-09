package com.joyance.demo.base.sort;

import com.alibaba.fastjson.JSON;

public class BubbleSort {

	public static void main(String[] args) {
		int array[] = new int[] {3,1,7,5,2,6};
		for(int i = array.length - 1 ;i > 1; i--) {
			for(int j = 0;j < i ;j++) {
				if(array[j] > array[j + 1]) {
					int swap = array[j];
					array[j] = array[j+1];
					array[j+1] = swap;
				}
			}
		}
		System.out.println(JSON.toJSONString(array));
	}
	
}
