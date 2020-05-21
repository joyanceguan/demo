package com.joyance.demo.base.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableTest {

	public static void main(String[] args) throws InterruptedException {
		List<Future<Integer>> futureList = new ArrayList<>();
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
		for(int i=1;i<7;i++){
			final int j = i;
			Callable<Integer> callable = new Callable<Integer>(){
				@Override
				public Integer call() throws Exception {
					Thread.sleep(5*1000);
					return j;
			}};
			list.add(callable);
//			Future<Integer> future = executorService.submit(callable);
//			futureList.add(future);
		}
		
		
		
		
	    long startTime = System.currentTimeMillis();
        futureList = executorService.invokeAll(list);
		for(Future<Integer> future : futureList){
			try {
				Integer i = future.get();
				System.out.println(i+"__"+(System.currentTimeMillis()-startTime));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
