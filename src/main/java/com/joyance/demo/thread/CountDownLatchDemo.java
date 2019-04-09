package com.joyance.demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatchDemo t = new CountDownLatchDemo();
		t.countDownLatch();
	}
	
	private void countDownLatch() throws InterruptedException{
		int num = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(num);
		CountDownLatch latch = new CountDownLatch(num);
		for(int i=0;i<num;i++){
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "开始干活了........");
					latch.countDown();
				}
			});
		}
		latch.await();
		System.out.println("活都干完了");
	}
	  
}
