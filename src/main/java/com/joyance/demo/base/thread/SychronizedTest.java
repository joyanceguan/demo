package com.joyance.demo.base.thread;


public class SychronizedTest implements Runnable{

	public synchronized void method(){
		try {
			System.out.println(Thread.currentThread().getName()+" begin");
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getName()+" end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		method();
	}
	
    public static void main(String[] args) {
    	SychronizedTest s1 = new SychronizedTest();
    	SychronizedTest s2 = new SychronizedTest();
    	Thread t1 = new Thread(s1);
    	Thread t2 = new Thread(s2);
    	t1.start();
    	t2.start();
	}
}
