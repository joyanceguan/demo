package com.joyance.demo.base.jdk8;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

public class OptionalTest {

	public static void main(String[] args) {
		Set<String> set1 = new HashSet<String>();
		set1.add("a");
		set1.add("b");
		Set<String> set2 = new HashSet<String>();
		set2.add("c");
		set2.add("d");
		Map<String,Set<String>> map = new HashMap<String,Set<String>>();
		map.put("1", set1);
		map.put("2", set2);
		
		map = null;
		
		Optional.ofNullable(map).ifPresent(o -> o.forEach((k, v) -> {
            if (CollectionUtils.isEmpty(v)) {
               System.out.println(k+"_"+v);
            } else {
                for (String column : v) {
                	System.out.println(k+"_"+v);
                }
            }
        }));
		
		
		Optional.ofNullable(set2).ifPresent(o -> o.forEach((v)->{
			System.out.println("========"+v);
		}));
		
		
		Assert.notEmpty(map,"不能为空");
		System.out.println("Assert notEmpty");
	}
}
