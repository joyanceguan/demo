package com.joyance.demo.base.sort;

public class Array1 {

	public boolean find(int target, int [][] array) {
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				System.out.println(array[i][j]);
				if(target == array[i][j]){
					return true;
				}
			}
		}
		return false;
    }
	
	public static void main(String[] args) {
		Array1 arr = new Array1();
		int[][] array = new int[2][3];
		
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				array[i][j] = (i+1)*100+j;
			}
		}
		boolean isHave = arr.find(103, array);
		System.out.println(isHave);
	}
}
