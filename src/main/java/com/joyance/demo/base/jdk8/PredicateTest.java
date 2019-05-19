package com.joyance.demo.base.jdk8;

import java.util.function.Predicate;

public class PredicateTest {

	public static void main(String[] args) {
		Predicate<Integer> predicate = x -> x > 100;
		predicate = predicate.and(x -> x % 2 == 0 );
		System.out.println(predicate.test(98));
		System.out.println(predicate.test(102));
		System.out.println(predicate.test(103));
	}
	
}
