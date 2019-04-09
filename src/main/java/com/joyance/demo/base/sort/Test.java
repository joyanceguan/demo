package com.joyance.demo.base.sort;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		String x = "abcabcdefgfghhijjkk";
		Map<String,Integer> map = new LinkedHashMap<String,Integer>();
		for(int i=0;i<x.length();i++){
			String key = ""+x.charAt(i);
			if(map.containsKey(key)){
				map.put(key, map.get(key)+1);
			}else{
				map.put(key, 1);
			}
		}
		
		for(String key:map.keySet()){
			if(map.get(key) == 1){
				System.out.println(key);
			}
		}
	}
}
