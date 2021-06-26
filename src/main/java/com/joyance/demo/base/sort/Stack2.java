package com.joyance.demo.base.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 逆波兰表达式，也叫后缀表达式，它将复杂表达式转换为可以依靠简单的操作得到计算结果的表达式
 * 例如(a+b)*(c+d)转换为ab+cd+*
 * 示例["4","13","5","/","+"] 等价于(4+(13/5)) = 6
 */
public class Stack2 {
	
	List<String> value;
	
	public Stack2(List<String> value){
		this.value = value;
	}

	public void operate(){
		Operate o = new Operate();
		ArrayStack stack = new ArrayStack();
		for(String v:value){
			if(isNumber(v)){
				stack.push(v);
			}else{
				String v1 = stack.pop();
				String v2 = stack.pop();
				int result = o.operate(Integer.parseInt(v2), Integer.parseInt(v1), v);
				stack.push(String.valueOf(result));
			}
		}
		System.out.println(stack.pop());
	}
	
	private boolean isNumber(String v){
		try{
			Integer.parseInt(v);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static void main(String[] args) {
		Stack2 stack = new Stack2(Arrays.asList("4","13","5","/","+"));
		stack.operate();
	}
	
	//=========================以下为加减乘除策略模式实现=========================
	private static interface Strategy{
		 public int doOperation(int num1, int num2);
	}
	
	private static class OperationAdd implements Strategy{
		@Override
		public int doOperation(int num1, int num2) {
			return num1 + num2;
		}
	}
	
	private static class OperationMinus implements Strategy{
		@Override
		public int doOperation(int num1, int num2) {
			return num1 - num2;
		}
	}
	
	private static class OperationMutiple implements Strategy{
		@Override
		public int doOperation(int num1, int num2) {
			return num1 * num2;
		}
	}
	
	private static class OperationDevide implements Strategy{
		@Override
		public int doOperation(int num1, int num2) {
			return num1 / num2;
		}
	}
	
	private static class Operate{
		
		Map<String, Strategy > OP_MAP  = new HashMap<String, Strategy>(){{  
		      put("+",new OperationAdd());  
		      put("—",new OperationMinus());     
		      put("*",new OperationMutiple());     
		      put("/",new OperationDevide());     
		}};  
		
		public int operate(int num1,int num2,String operate){
			return OP_MAP.get(operate).doOperation(num1, num2);
		}
        
	}
}
