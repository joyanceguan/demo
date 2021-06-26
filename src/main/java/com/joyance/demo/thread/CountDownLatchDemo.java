package com.joyance.demo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatchDemo c = new CountDownLatchDemo();
		CountDownLatch cdl = new CountDownLatch(3);
		c.new Test1T21(cdl).start();
        c.new Test1T22(cdl).start();
        c.new Test1T23(cdl).start();
        try {
            cdl.await(10000, TimeUnit.MILLISECONDS);
            System.out.println("Success...");
            // 继续执行下面的逻辑...(略)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
	}
	
	class Test1T21 extends Thread {

	    private CountDownLatch cdl;

	    Test1T21(CountDownLatch cdl) {
	        this.cdl = cdl;
	    }

	    public void run() {
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Test1T21 name =" + Thread.currentThread().getName());
	        cdl.countDown();
	    }
	}
	
	class Test1T22 extends Thread {

	    private CountDownLatch cdl;

	    Test1T22(CountDownLatch cdl) {
	        this.cdl = cdl;
	    }

	    public void run() {
	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Test1T22 name =" + Thread.currentThread().getName());
	        cdl.countDown();
	    }
	}
	
	class Test1T23 extends Thread {

	    private CountDownLatch cdl;

	    Test1T23(CountDownLatch cdl) {
	        this.cdl = cdl;
	    }

	    public void run() {
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("Test1T23 name =" + Thread.currentThread().getName());
	        cdl.countDown();
	    }
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
