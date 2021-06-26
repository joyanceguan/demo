package com.joyance.demo.base.limiting;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

	public void testSimple() {
	      RateLimiter limiter = RateLimiter.create(1);
	      for(int i = 1; i < 10; i = i + 2) {
	          double waitTime = limiter.acquire(i);
	          System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
	      }
	}
	
	public static void main(String[] args) {
		RateLimiterTest r = new RateLimiterTest();
		r.testSimple();
	}
}
