package com.joyance.demo.base.sort;

public class QuickSort3 {

	private void sort(int[] array,int from,int to){
		if(from<to){
			int base = array[from];
			int start = from;
			int end = to;
			while(start != end){
				while(array[end] >= base && start < end){
					end--;
				}
				if(start < end){
					array[start++] = array[end];
					array[end] = base;
				}
				
				while(array[start] <= base && start < end){
					start++;
				}
				if(start < end){
					array[end] =  array[start];
					array[start] = base;
				}
			}
			sort(array,start+1,to);
			sort(array,from,start);
		}
		
	}
	
	private void printArray(int[] array){
		for(int i:array){
			System.out.print(i+",");
		}
	}
	
	public static void main(String[] args) {
		int[] array = {12,20,5,16,15,1,30,45,7,21};
		QuickSort3 sort = new QuickSort3();
		sort.sort(array,0,array.length-1);
		sort.printArray(array);
	}
}
