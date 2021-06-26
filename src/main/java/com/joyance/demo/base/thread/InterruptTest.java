package com.joyance.demo.base.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.joyance.demo.utils.HttpClientUtils;


public class InterruptTest {
	
	ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
	
	public void x(int n){
		CountDownLatch c = new CountDownLatch(2);
		List<Future<String>> list = new ArrayList<Future<String>>();
		for(int i = 1;i < 3 ; i++){
			final int j = i;
			Future<String> future = executorService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					String threadname = Thread.currentThread().getName();
					int realSleepTime = 0;
					if(j==1){
						realSleepTime = 1000;
					}else{
						realSleepTime = j*10000;
					}
					
					System.out.println(n+"   "+threadname+"执行"+j+"了");
					
					Thread.sleep(realSleepTime);
					System.out.println(n+"   "+threadname +" " + realSleepTime);
					c.countDown();
					return threadname;
				}
			});
			list.add(future);
		}
	    try {
			c.await(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//	    for(Future<String> future:list){
//	    	try {
//	    		future.cancel(true);
//				System.out.println(future.get()+"取消了");
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//	    }
	}
	
	public void x(){
		executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
		Future<String> future1 = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"执行前1");
				String result = HttpClientUtils.doGet("http://10.37.50.83:8080/dmp/get?param={\"fe_ids\":[1547],\"platform\":\"jupiter\",\"row_keys\":[\"811619471446006\"]}", null, null);
				System.out.println(threadName+"执行后1" + result);
				return threadName;
			}
		});
		
//		Future<String> future2 = executorService.submit(new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				String threadName = Thread.currentThread().getName();
//				System.out.println(threadName+"执行前2");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(threadName+"执行后2");
//				return threadName;
//			}
//		});
		
		try {
//			String name = future1.get(2, TimeUnit.SECONDS);
//			System.out.println(name+" f1");
			System.out.println(future1.isCancelled() + "  " + future1.isDone());
			future1.cancel(true);
//			String v = future1.get(2, TimeUnit.SECONDS);
//			System.out.println(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(future1.isDone()+"======="+future1.isCancelled());
		
//		try {
//			String name = future2.get();
//			System.out.println(name+" f2");
//			System.out.println(future2.isCancelled() + " " + future2.isDone());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	

	public static void main(String[] args) throws InterruptedException, ExecutionException {
	   InterruptTest t = new InterruptTest();
//	   for(int i=1;i<5;i++){
//		   t.x(i);
//	   }	
	   t.x();
	}
	
}
