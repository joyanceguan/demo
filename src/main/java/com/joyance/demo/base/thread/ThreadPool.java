package com.joyance.demo.base.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = new ThreadPoolExecutor(2, 4, 0L, 
                TimeUnit.MILLISECONDS, 
                new LinkedBlockingQueue<>(2), 
                Executors.defaultThreadFactory(), 
                new ThreadPoolExecutor.AbortPolicy(){});
		
		for(int i=0;i<3;i++){
			if(i== 2){
				Thread.sleep(6000);
			}
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName()+" begin");
						Thread.sleep(5000);
						System.out.println(Thread.currentThread().getName()+" end");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();
	}
}
