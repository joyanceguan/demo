package com.joyance.demo.base.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest {
	
	public static  void test1(){
		SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
        new Customer(queue).start();
        new Product(queue).start();
	}
	
	public static void test2(){
		SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
		ExecutorService e1 = Executors.newSingleThreadExecutor();
		ExecutorService e2 = Executors.newSingleThreadExecutor();
		e1.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean s1 = queue.offer(1);
				boolean s2 = queue.offer(2);
				System.out.println(s1+"生产完成"+s2);
			}
		});
		
		
        e2.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("消费完成"+queue.take());
					System.out.println("消费完成"+queue.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String[] args) throws InterruptedException {
		test2();
    }

    static class Product extends Thread{
        SynchronousQueue<Integer> queue;
        public Product(SynchronousQueue<Integer> queue){
            this.queue = queue;
        }
        @Override
        public void run(){
            while(true){
                int rand = new Random().nextInt(1000);
                System.out.println("生产了一个产品："+rand);
                System.out.println("等待三秒后运送出去...");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.offer(rand);
                System.out.println("产品生成完成："+rand);
            }
        }
    }
    static class Customer extends Thread{
        SynchronousQueue<Integer> queue;
        public Customer(SynchronousQueue<Integer> queue){
            this.queue = queue;
        }
        @Override
        public void run(){
            while(true){
                try {
                    System.out.println("消费了一个产品:"+queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("------------------------------------------");
            }
        }
    }
}
