package com.joyance.demo.base.sort;

/**
 * 下面的字符串中包含小括号，请编写一个函数判断字符串中的括号是否合法，所谓合法，就是括号成对出现:
 * sdf(ds(ew(we)rw)rwqq)qwewe   合法
 * (sd(qwqw)sd(sd))  合法
 * ()()sd()(sd()fw))(  不合法
 */
public class Stack1 {
	
	private String value;
	
	public Stack1(String value){
		this.value = value;
	}
	
	public boolean legal(){
		ArrayStack stack = new ArrayStack();
		for(int i=0;i<value.length();i++){
			String v = String.valueOf(value.charAt(i));
			if(v.equals("(")){
				stack.push(v);	
			}else if(v.equals(")")){
				if(stack.isEmpty()){
					return false;
				}else{
					stack.pop();
				}
			}
		}
		return stack.isEmpty();
	}
	
	public static void main(String[] args) {
		Stack1 stack = new Stack1("(sd(qwqw)sd(sd))");
		System.out.println(stack.legal());
	}
}
