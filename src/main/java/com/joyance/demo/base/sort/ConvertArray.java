package com.joyance.demo.base.sort;

import com.alibaba.fastjson.JSON;

public class ConvertArray {

	//1 2 3 4 5 6 7
	public void convertArray(int[] array,int index){
		int length = array.length;
		for(int i = length - 1;i >= index; i--){
			int temp = array[length - 1];
			for(int j = length - 1 ; j > 0; j--){
				array[j] = array[j-1];
			}
			array[0] = temp;
		}
		System.out.println(JSON.toJSONString(array));
	}
	
	
	public void swap(int[] array,int begin,int end){
		int temp = array[begin];
		array[begin] = array[end];
		array[end] = temp;
	}
	
	public static void main(String[] args) {
		ConvertArray ca = new ConvertArray();
		int[] array = {1,2,3,4,5,6,7};
		ca.convertArray(array, 5);
	}
}
