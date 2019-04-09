package com.joyance.demo.base.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
		for(int i=1;i<10;i++){
			final int j = i;
			Callable<Integer> callable = new Callable<Integer>(){
				@Override
				public Integer call() throws Exception {
					Thread.sleep(5000);
					return j;
			}};
			list.add(callable);
		}
		
		
		List<Future<Integer>> futureList = executorService.invokeAll(list);
		for(Future<Integer> future : futureList){
			try {
				Integer i = future.get();
				System.out.println(i);
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
