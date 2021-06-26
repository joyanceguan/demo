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
    
    public static int fibonacci3(int index){
    	ArrayQueue q = new ArrayQueue();
    	if(index < 3){
    		return 1;
    	}
    	for(int i=1;i<index+1;i++){
    		if(i < 3){
    			q.enqueue("1");
    		}else{
    			int pre = Integer.parseInt(q.dequeue());
    			int next = Integer.parseInt(q.dequeue());
    			q.enqueue(next+"");
    			q.enqueue((pre+next)+"");
    		}
    	}
    	int v = Integer.parseInt(q.dequeue());
    	if(!q.isEmpty()){
    		v = Integer.parseInt(q.dequeue());
    	}
        return v;    	
    }
	
	public static void main(String[] args) {
		int result = fibonacci3(5);
		System.out.println(result);
	}
}
