package com.joyance.demo.base.jdk8;

public class BaseLambda {

	interface MathOperation {
	      int operation(int a, int b);
	}
	
	private int operate(int a, int b, MathOperation mathOperation){
	      return mathOperation.operation(a, b);
	}
	
	interface GreetingService {
	      void sayMessage(String message);
    }
	
    static String name ="hello world ";
	
	public static void main(String[] args) {
		BaseLambda lmd = new BaseLambda();
		// 类型声明
	    MathOperation sumAddition = (int a, int b) -> a + b;
	    int sum = lmd.operate(1, 2, sumAddition);
	    System.out.println(sum);
	    
	    //不用类型声明
	    MathOperation minusAddition = (a,b) -> a-b;
	    int minus = lmd.operate(3, 1, minusAddition);
	    System.out.println(minus);
	    
	    //大括号返回
	    MathOperation mutipleAddition = (a, b) -> {return a * b;};
	    int mutiple = lmd.operate(3, 2, mutipleAddition);
	    System.out.println(mutiple);
	    
	    //没有大括号及返回语句
	    MathOperation divideAddition = (a,b) -> a/b;
	    int divide = lmd.operate(3, 1, divideAddition);
	    System.out.println(divide);
	    
	}
}
