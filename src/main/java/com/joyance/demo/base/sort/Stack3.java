package com.joyance.demo.base.sort;

/**
 * 实现一个有min方法的栈，除了常见的push，pop方法以外，提供一个min方法，返回栈里最小的元素
 * 且时间复杂度为o(1)
 */
public class Stack3 {

	ArrayStack stack1 = new ArrayStack();
	ArrayStack stack2 = new ArrayStack();
	
	public void push(String value){
		stack1.push(value);
		if(stack2.isEmpty() || Integer.parseInt(stack2.top()) > Integer.parseInt(value)){
			stack2.push(value);
		}else{
			stack2.push(stack2.top());
		}
	}
	
	public String pop(){
		stack2.pop();
		return stack1.pop();
	}
	
	public String min(){
		return stack2.top();
	}
	
	public static void main(String[] args) {
		Stack3 stack3 = new Stack3();
		stack3.push("3");
		stack3.push("2");
		stack3.push("6");
		stack3.push("4");
		stack3.push("1");
		
//		stack3.pop();
		
		System.out.println(stack3.min());
	}
}
