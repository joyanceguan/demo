package com.joyance.demo.base.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class X {

	public static void main(String[] args) throws InterruptedException {
//		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 4, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(3));
		
		CountDownLatch c = new CountDownLatch(7);
		for(int i= 1;i<8;i++){
			final int j = i;
			Thread.sleep(50);
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(j+" "+executorService.getActiveCount()+"_"+executorService.getCompletedTaskCount()+"_"+executorService.getLargestPoolSize()+"_"+executorService.getQueue().size());
					try {
//					    LockSupport.parkNanos(9000000000l);
//						LockSupport.park();
						Thread.sleep(5000);
//						LockSupport.unpark(thread);
						c.countDown();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
		}
		c.await();
		Thread.sleep(3000);
		System.out.println("finish "+executorService.getActiveCount()+"_"+executorService.getCompletedTaskCount()+"_"+executorService.getLargestPoolSize()+"_"+executorService.getCorePoolSize()+"_"+executorService.getPoolSize()+"_"+executorService.getTaskCount()+"_"+executorService.getQueue().size());
	
	}
}
