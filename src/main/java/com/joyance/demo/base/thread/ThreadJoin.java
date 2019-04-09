package com.joyance.demo.base.thread;

public class ThreadJoin {

	public static void main(String[] args) throws InterruptedException {
		Runnable r1 = new Runnable(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" start");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}};
		
		Runnable r2 = new Runnable(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" start");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		
		System.out.println("end");
	}
}
