package com.joyance.demo.utils;

import java.util.Map;
import java.util.TreeMap;

public class Test {
	
	public static void main(String[] args) {
		String x = "key value key value key abc";
		String[] array = x.split(" ");
		Map<String,Integer> map = new TreeMap<String,Integer>();
		for(String str:array){
			if(map.containsKey(str)){
				int count = map.get(str);
				map.put(str, count+1);
			}else{
				map.put(str, 1);
			}
		}
		Map<String,Integer> map2 = new TreeMap<String,Integer>();
		for(String key:map.keySet()){
			String z = map.get(key)+"_"+key;
			map2.put(z, 0);
		}
		System.out.println(map2);
	}
	
	
}
