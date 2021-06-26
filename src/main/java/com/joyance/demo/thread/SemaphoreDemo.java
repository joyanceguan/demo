package com.joyance.demo.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import com.alibaba.fastjson.JSON;

public class SemaphoreDemo {

	public static void main(String[] args) throws Exception{
		final Semaphore semaphore = new Semaphore(3);
	    int threads = 4;
		ExecutorService executorService = Executors.newFixedThreadPool(threads);
		for(int i = 0;i < threads ;i++){
			executorService.execute(new Runnable(){
				@Override
				public void run() {
					try {
						semaphore.acquire(3);
						System.out.println(Thread.currentThread().getName()+"占据了窗口");
						Thread.sleep(3000);
						System.out.println(Thread.currentThread().getName()+"释放了窗口");
						semaphore.release(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			}});
		}
		
		List<Integer> list = new ArrayList<Integer>(0);
		list.add(2);
		list.add(1);
//		System.out.println(JSON.toJSONString(list));
		
		Iterator<Integer> it = list.iterator();
		it.forEachRemaining(s->{System.out.println(it.next());});
		
//		list.forEach(s->{System.out.println(s+"==");});
		
		System.out.println(8>>1);
		
	    HashMap<String,Object> map = new HashMap<String,Object>();
	    for(int i=0;i<11;i++){
	    	map.put(i+"", true);
	    }
	    map.put("a", true);
	    map.put("b", true);
	}
}
