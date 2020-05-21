package com.joyance.demo.base.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class GuavaThreadExecutors {
	
    int i = 0;
    
    public int addI(){
    	System.out.println(++i);
    	return i;
    }
    
    public void runable() throws InterruptedException{
		int threadNum = 2;
		ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(threadNum));
		CountDownLatch latch = new CountDownLatch(threadNum);
		for(int i = 0;i<threadNum;i++){
			listeningExecutorService.submit(new Runnable() {
				@Override
				public void run() {
					addI();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					latch.countDown();
				}
			});
		}
		latch.await();
		listeningExecutorService.shutdown();
    }
    
    public void callable(){
    	int threadNum = 2;
		ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(threadNum));
		for(int i = 0;i<threadNum;i++){
			ListenableFuture<Integer> future = listeningExecutorService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int result = addI();
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return result;
				}
			});
			Futures.addCallback(future, new FutureCallback<Integer>(){

				@Override
				public void onSuccess(Integer result) {
					System.out.println(Thread.currentThread().getName()+" get result="+result);
				}

				@Override
				public void onFailure(Throwable t) {
					System.out.println(Thread.currentThread().getName()+" get result failed");
				}
				
			});
		}
		listeningExecutorService.shutdown();
    }

	public static void main(String[] args) throws Exception {
		GuavaThreadExecutors t1 = new GuavaThreadExecutors();
//		t1.runable();
		t1.callable();
	}
}
