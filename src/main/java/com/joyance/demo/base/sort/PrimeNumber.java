package com.joyance.demo.base.sort;

import java.util.LinkedList;
import java.util.List;

public class PrimeNumber {

	public List<Integer> method(int num){
		List<Integer> list = new LinkedList<Integer>();
		int j;
		boolean flag;
		for(int i=2;i<num;i++){
          flag=false;
	      for(j=2;j<i/2+1;j++){
		    if(i%j==0){
		      flag=true;
		      break;
		    }
		  }
		  if(!flag){
			  list.add(i);
		  }
	    }
		return list;
	}
	
	public static void main(String[] args) {
		PrimeNumber p = new PrimeNumber();
		long beginTime = System.currentTimeMillis();
		List<Integer> list = p.method(100);
		System.out.println(list);
		long endTime = System.currentTimeMillis();
		System.out.println("耗时："+(endTime - beginTime));
	}
}
