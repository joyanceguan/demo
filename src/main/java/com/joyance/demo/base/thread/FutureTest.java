package com.joyance.demo.base.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.joyance.demo.utils.HttpClientUtils;

public class FutureTest {
	
	public void x(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void test(){
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
		Future<String> future1 = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				String threadName = Thread.currentThread().getName();
				System.out.println(threadName+"执行前1");
//				x();
				Thread.sleep(1);
				String result = HttpClientUtils.doGet("http://localhost:8188/solar-web/pub_check", null, null);
				System.out.println(threadName+"执行后1"+result);
				return threadName;
			}
		});
		System.out.println(future1.isCancelled() + "  " + future1.isDone()+"========");
		future1.cancel(true);
		System.out.println(future1.isCancelled() + "  " + future1.isDone()+"--------");
	}
	
	public static void main(String[] args) {
		FutureTest f = new FutureTest();
		f.test();
	}
}
