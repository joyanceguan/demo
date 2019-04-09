package com.joyance.demo.base.sort;

public class Fibonacci {

	// 1 1 2 3 5 8
	public static int fibonacci(int index) {
		if(index < 3) {
			return 1;
		}
		else {
			return fibonacci(index - 1) + fibonacci(index - 2);
		}
	}
	
	
	// 1 1 2 3 5 8
    public static int fibonacci2(int index) {
    	if(index < 3) {
			return 1;
		}
    	int x1 = 1;
    	int x2 = 1;
        for(int i = 3; i < index+1; i++) {
        	int temp = x1 + x2;
        	x1 = x2;
        	x2 = temp;
        }
        return x2;
    }
	
	public static void main(String[] args) {
		int result = fibonacci2(6);
		System.out.println(result);
	}
}
