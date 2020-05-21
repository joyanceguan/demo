package com.joyance.demo.base.jdk8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamTest {

	public static void main(String[] args) throws IOException {
		long count = IntStream.range(1,100)
				              .filter( i -> i % 2 == 0 )
				              .count();
		System.out.println("count:" + count);
		
		
		Stream<String> stream = Files.lines(Paths.get("/Users/joyance/Documents/y.txt"));
		List<String> list = stream.flatMap(line -> Arrays.stream(line.split("\n"))).distinct().collect(Collectors.toList());
	    System.out.println(list);
	}
}
