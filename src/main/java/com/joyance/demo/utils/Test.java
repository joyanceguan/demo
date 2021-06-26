package com.joyance.demo.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Test {
	
	public static void main(String[] args) {
//		String x = "key value key value key abc";
//		String[] array = x.split(" ");
//		Map<String,Integer> map = new TreeMap<String,Integer>();
//		for(String str:array){
//			if(map.containsKey(str)){
//				int count = map.get(str);
//				map.put(str, count+1);
//			}else{
//				map.put(str, 1);
//			}
//		}
//		Map<String,Integer> map2 = new TreeMap<String,Integer>();
//		for(String key:map.keySet()){
//			String z = map.get(key)+"_"+key;
//			map2.put(z, 0);
//		}
//		System.out.println(map2);
		
		List<String> list1= Arrays.asList("1481420109","1483587393","1406404281","1198038783","1397001176","1446900265","1389247872","1446900258","1412700148","1426395631","1292100109","1393811472","1209197199","1397001176","1409515117","1397001203","1397000025","1268854994","1397001181","1138684146","1406000009","1397871759","1209354864","1412700148","1389050537","1198513495","1288900008","1198038783","1406000012","1460442816","1292100011","1406404281","1483587393","1393811472","1414673045","1481420109");
	    List<String> list2 = Arrays.asList("1481420109","1209354864","1397871759","1446900265","1409515117","1268854994","1460442816","1138684146","1426395631","1397000025","1414673045","1397001181","1412700148","1292100011","1397001203","1406404281","1393811472","1198513495","1483587393","1406000009","1209197199","1198038783","1406000012","1292100109","1389247872","1397001176","1389050537","1446900258","1288900008");
	    System.out.println(list1.stream().distinct().count());
	    
	    System.out.println("UNIONPAY,SANXINGPAY,HUAWEIPAY,XIAOMIPAY,MEIZUPAY,OPPOPAY,VIVOPAY".length());
	}
	
	
}
