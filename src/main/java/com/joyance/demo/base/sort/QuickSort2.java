package com.joyance.demo.base.sort;

import com.alibaba.fastjson.JSON;

public class QuickSort2 {

	public static void main(String[] args) {
//		int[] array = {6,1,3,7,9,5,2,8};
		int[] array = {6,1,2,7,9,3,4,5,10,8,11,-1};
		int begin = 0;
		int end = array.length - 1;
		sort(array,begin,end);
		System.out.println(JSON.toJSONString(array));
	}
	
	public static void sort(int[] array,int begin,int end){
		if(begin+1 <= end){
			int before = begin;
			int after = end;
			int base = array[begin++];
			while(begin < end){
				while(base < array[end] && end > begin){
					end--;
				}
				while(base > array[begin] && begin < end){
					begin++;
				}
				if(begin < end){
					swap(array,begin,end);
				}else{
					swap(array,before,end);
					System.out.println("+"+JSON.toJSONString(array));
					sort(array,before,end);
					sort(array,end+1,after);
				}
			}
		}
	}
	
	public static void swap(int[] array,int begin,int end){
		int temp = array[begin];
		array[begin] = array[end];
		array[end] = temp;
	}
}
