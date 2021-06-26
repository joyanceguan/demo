package com.joyance.demo.base.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch c = new CountDownLatch(2);
		ExecutorService s = Executors.newFixedThreadPool(2);
		for(int i = 1; i<2;i++){
			s.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(3000);
						c.countDown();
						System.out.println(Thread.currentThread()+" finish");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		c.await(1, TimeUnit.SECONDS);
		System.out.println("all finish");
	}
}
