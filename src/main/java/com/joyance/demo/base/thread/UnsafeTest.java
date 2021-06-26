package com.joyance.demo.base.thread;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;


public class UnsafeTest {

    public int a = 0;
	
	public long b = 0;
	
	public static String c= "123";
	
	public static Object d= null;
	
	public static int e = 100;
	
	
	public static void main(String[] args) {
		ThreadGroup threadGroup1 = new ThreadGroup("group1") { // ThreadGroup
			// unchecked exception
			//
			            public void uncaughtException(Thread t, Throwable e) {
			                System.out.println(t.getName() + ": " + e.getMessage());
			         } };
			//
			           Thread thread1 = new Thread(threadGroup1, new Runnable() {
			            public void run() {
			// unchecked
			throw new RuntimeException("测试异常"); }
			});
			        thread1.start();
	}
}
