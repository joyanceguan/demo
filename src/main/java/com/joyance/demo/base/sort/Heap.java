package com.joyance.demo.base.sort;

import com.alibaba.fastjson.JSON;

/**
 * 最小堆存储
 */
public class Heap {
	
	public int[] insert(int[] array){
		int[] result = new int[array.length];
		for(int i=0;i < array.length;i++){
			int value = array[i];
			result[i] = value;
			if(i == 0){
				continue;
			}
			int index = i;
			while(index > 0){
				int parentValue = result[(index-1)/2];
				if(parentValue > value){
					result[(index-1)/2] = value;
					result[index] = parentValue;
				}
				index = (index-1)/2;
			}
		}
		System.out.println("insert "+JSON.toJSONString(result));
		return result;
	}
	
	public int[] init(int[] array){
		for(int i=0;i <= (array.length - 1)/2;i++){
			int leftChildIndex = 2 * i + 1;
			int currentIndex = i;
			shift(array,leftChildIndex,currentIndex);
		}
		System.out.println("init "+JSON.toJSONString(array));
		return array;
	}
	
	public void shift(int[] array,int leftChildIndex,int currentIndex){
		while(leftChildIndex < array.length){
			int minChildValue = -1;
			int minChileIndex = -1;
			//比较左右孩子，取做小值
			if(leftChildIndex+1 < array.length && array[leftChildIndex+1] < array[leftChildIndex]){
				 minChildValue = array[leftChildIndex+1];
				 minChileIndex = leftChildIndex+1;
			}else{
				 minChildValue = array[leftChildIndex];
				 minChileIndex = leftChildIndex;
			}
			//与父节点比小
			if(minChildValue < array[currentIndex]){
				int temp = array[currentIndex];
				array[currentIndex] = minChildValue;
				array[minChileIndex] = temp;
				
				currentIndex = 0;
				leftChildIndex = 1;
			}else{
				currentIndex = leftChildIndex;
				leftChildIndex = 2*leftChildIndex+1;
			}
		}
	}
	
	public int[] removeMin(int[] array){
		int last = array[array.length-1];
		array[0] = last;
		int[] result = new int[array.length-1];
		for(int i=0;i<array.length-1;i++){
			result[i] = array[i];
		}
		System.out.println("remove min " + JSON.toJSONString(result));
		return init(result);
	}
	
	/**
	 * 一个数组获取最大几个值
	 * @param array
	 * @param n
	 */
	public void maxN(int[] array,int n){
		int[] result = new int[n];
		for(int i=0;i<n;i++){
			result[i] = array[i];
		}
		init(result);
		for(int i = n;i < array.length;i++){
			if(array[i] > result[0]){
				result[0] = array[i];
				init(result);
			}
		}
		System.out.println("maxN " + JSON.toJSONString(result));
	}
	
	public static void main(String[] args) {
		int[] array = new int[]{7,6,10,4,8,12,14,3,9};
		Heap heap = new Heap();
		heap.insert(array);
		heap.init(array);
////		array = heap.init(array);
//		int[] remove = heap.removeMin(array);
//		heap.removeMin(remove);
//		System.out.println(JSON.toJSONString(array));
		heap.maxN(array, 5);
	}
}
