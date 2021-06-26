package com.joyance.demo.base.sort;

import java.util.HashSet;
import java.util.Set;

public class BitMap {
	
    private int[] array = new int[10];
	
	public void push(Set<Integer> set){
		for(int value:set){
			int n = getN(value);
			int p = getP(value);
			array[n] = 1<<p|array[n];
		}
	}
	
	public boolean exist(int val){
		int n = getN(val);
		int p = getP(val);
		int value = array[n];
		int x= (value & 1<<p);
		return x != 0;
	}
	
    private int getN(int val){
    	return val/32;
    }
    
    private int getP(int val){
    	return val%32;
    }
	
	public void printArray(){
		System.out.println("开始打印数组");
		for(int i:array){
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		BitMap bm = new BitMap();
		Set<Integer> set = new HashSet<Integer>();
		set.add(70);
		set.add(120);
		bm.push(set);
//		bm.printArray();
		
		System.out.println(bm.exist(64));
	}
}
