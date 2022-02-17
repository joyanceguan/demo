package com.joyance.demo.main;

public class Array1 {
	
	
	public static int[] findTarget(int target,int[] array){
		int[] result = new int[]{-1,-1};
		
		int point = array.length - 1;
		
		boolean findFrom = false;
		boolean findTo = false;
		
		
		for(int i =0 ;i<array.length;i++){
			
			if(!findFrom && array[i] == target){
				result[0] = i;
				findFrom = true;
			}
			
			if(!findTo){
				int end = array[point - i];
				if(end == target){
					result[1] = point - i;
					findTo = true;
				}
			}
			
			
			if(findFrom && findTo){
				break;
			}
		}
		return result ;
	}
    
	public static void main(String[] args) {
		int[] array = {1,0,2,5,9,4,5,9,7,5};
		int[] result = Array1.findTarget(9, array);
	    for(int index : result){
	    	System.out.println(index);
	    }
		
	}
}
