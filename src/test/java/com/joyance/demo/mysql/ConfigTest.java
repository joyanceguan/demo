package com.joyance.demo.mysql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.joyance.demo.mybatis.mapper.CopyConfigMapper;
import com.joyance.demo.mybatis.persistence.ConfigCopy;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class ConfigTest {

	@Autowired
	CopyConfigMapper copyConfigMapper;
	
	@Test
	public void testSelectById(){
		ConfigCopy config = copyConfigMapper.selectById(1);
		System.out.println(JSON.toJSONString(config));
	}
	
	@Test
	public void testSave(){
		Date now = new Date();
		
		ConfigCopy cc = new ConfigCopy();
		cc.setC_desc("c_desc");
		cc.setC_key("c_key");
		cc.setC_value("c_value");
		cc.setIndex_key(1002L);
		cc.setCreate_time(now);
		cc.setUpdate_time(now);
		
		ConfigCopy cc2 = new ConfigCopy();
		cc2.setC_desc("c_desc");
		cc2.setC_key("c_key");
		cc2.setC_value("c_value");
		cc2.setIndex_key(1001L);
		cc2.setCreate_time(now);
		cc2.setUpdate_time(now);
		
		List<ConfigCopy> list = new ArrayList<ConfigCopy>();
		list.add(cc);
		list.add(cc2);
		int size = copyConfigMapper.batchSave(list);
		System.out.println(size);
	}
	
	/**
	 * 目标插入200万条数据，启动40个线程，每个线程插入5万条
	 * @throws InterruptedException 
	 * 
	 */
	@Test
	public void testMutiThreadBatchSave() throws InterruptedException{
		int threads = 40;
		int batchSaveNum = 100;
		int totalNum = 50000;
		System.out.println("执行开始");
		long start = System.currentTimeMillis();
		CountDownLatch cl = new CountDownLatch(threads);
		ExecutorService executors = Executors.newFixedThreadPool(threads);
		for(int i=0;i<threads;i++){
			executors.execute(new SaveThread(cl,totalNum,batchSaveNum));
		}
		cl.await();
		long end = System.currentTimeMillis();
		System.out.println("执行完毕,总耗时"+(end-start));
	}
	
	private class SaveThread implements Runnable{
		
		//一次插入数据库多少条
		private int batchSaveNum;
		
		//该任务一共要插入多少条数据
		private int totalNum;
		
		//循环插入的次数
		private int saveTimes;
		
		private CountDownLatch cl;
		
		public SaveThread(CountDownLatch cl,int totalNum,int batchSaveNum){
			this.cl = cl;
			this.batchSaveNum = batchSaveNum;
			this.totalNum = totalNum;
			if(totalNum % batchSaveNum==0){
				saveTimes = totalNum/batchSaveNum;
			}else{
				saveTimes = (totalNum/batchSaveNum) + 1;
			}
		}

		@Override
		public void run() {
			int times = 0;
			while(times < saveTimes){
				int batchNum = batchSaveNum;
				if(times == saveTimes - 1){
					batchNum = totalNum % batchSaveNum;
				}
				List<ConfigCopy> list = new ArrayList<ConfigCopy>();
				Date now = new Date();
				Random r = new Random();
				for(int i=0;i<batchNum;i++){
					int index_key = r.nextInt(100000000);
					ConfigCopy cc = new ConfigCopy();
					cc.setC_desc(Thread.currentThread().getName());
					cc.setC_key(index_key+"");
					cc.setC_value(index_key+"_value");
					cc.setIndex_key(Long.valueOf(index_key));
					cc.setCreate_time(now);
					cc.setUpdate_time(now);
					list.add(cc);
				}
				int flags = copyConfigMapper.batchSave(list);
				if(flags!=batchNum){
					System.out.println(Thread.currentThread().getName()+"执行了"+flags);
				}
				times++;
		    }
			cl.countDown();
		}
	}
	
}
