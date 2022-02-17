package com.joyance.demo.base.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class SingleThreadPoolLoop implements Runnable{

	private LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
	
	public void add(Runnable runnable){
		queue.add(runnable);
	}
	
	@Override
	public void run() {
        while(true){
        	try {
				Thread.sleep(1000);
				queue.take().run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }		
	}

	public static void main(String[] args) {
		SingleThreadPoolLoop s = new SingleThreadPoolLoop();
		Thread t = new Thread(s);
		t.start();
		s.add(new Runnable() {
			@Override
			public void run() {
               System.out.println("hello world");				
			}
		});
		
		s.add(new Runnable() {
			@Override
			public void run() {
               System.out.println("hello java");				
			}
		});
		
		
		for(int i =0 ;i<5;i++){
			s.add(new Runnable() {
				
				@Override
				public void run() {
	               System.out.println("hello time "+System.currentTimeMillis());				
				}
			});
		}
		
	}
}
