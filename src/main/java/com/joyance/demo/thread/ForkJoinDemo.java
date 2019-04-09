package com.joyance.demo.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * fork join求素数
 * @author guanyue
 */
public class ForkJoinDemo extends RecursiveTask<List<Integer>>{

	private static final long serialVersionUID = 1L;
	
	int limit=20000;
	
	int begin;
	
	int end;
	

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public ForkJoinDemo(int begin,int end){
		this.begin = begin;
		this.end = end;
	}
	
	public List<Integer> method(int begin,int end){
		int j;
		boolean flag;
		List<Integer> list = new LinkedList<Integer>();
		for(int i=begin;i<end;i++){
          flag=false;
	      for(j=2;j<i/2+1;j++){
		    if(i%j==0){
		      flag=true;
		      break;
		    }
		  }
		  if(!flag){
			  list.add(i);
		  }
	    }
		return list;
	}
	
	@Override
	protected List<Integer> compute() {
		List<Integer> resultlist = new ArrayList<Integer>();
		if(end > begin && end - begin < limit+1){
			List<Integer> rl = method(begin,end);
			for(Integer i:rl){
				resultlist.add(i);
			}
		}else{
			List<ForkJoinDemo> list = new ArrayList<ForkJoinDemo>();
			int x = begin;
			while(x < end){
				ForkJoinDemo fj = null;
				if(x+limit<end){
					 fj = new ForkJoinDemo(x,x+=limit);
				}else{
					 fj = new ForkJoinDemo(x,end);
					 x = end;
				}
				list.add(fj);
				fj.fork();
			}
			for(ForkJoinDemo fj:list){
				List<Integer> rl = fj.join();
				for(Integer i:rl){
					resultlist.add(i);
				}
			}
			
//			int middle = (end + begin)/2;
//			
//				ForkJoinDemo left = new ForkJoinDemo(begin,middle);
//				ForkJoinDemo right = new ForkJoinDemo(middle+1,end);
//				left.fork();
//				right.fork();
//			
//			List<Integer> l = left.join();
//			List<Integer> r = right.join();
//			resultlist.addAll(l);
//			resultlist.addAll(r);
		}
		return resultlist;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int thread = 4;
		int maxNum = 1000000;
		long beginTime = System.currentTimeMillis();
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinDemo fj = new ForkJoinDemo(2,maxNum);
		Future<List<Integer>> future = pool.submit(fj);
		System.out.println(future.get());
		long endTime = System.currentTimeMillis();
		System.out.println("耗时："+(endTime - beginTime));
	}
}
