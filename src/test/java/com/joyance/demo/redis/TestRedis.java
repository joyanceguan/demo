package com.joyance.demo.redis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class TestRedis {

	@Resource
	RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void testX(){
		redisTemplate.opsForValue().set("joy demo", "hello world111");
		
		String value = (String) redisTemplate.opsForValue().get("joy demo");
		System.out.println("get from redis value = "+value);
		
	}
	
	@Test
	public void testY(){
		List<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(6);
		list.add(3);
		list.add(7);
		list.add(5);
//        //放入排好序的投票id		
//	    redisTemplate.opsForSet().add("voteIds", list);
//	    Object o = redisTemplate.opsForSet().members("voteIds");
//	    System.out.println(JSON.toJSONString(o));
//		
//	    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
//	    map.put(1, 100);
//	    map.put(3, 300);
//	    map.put(6, 600);
//	    map.put(7, 700);
//	    map.put(5, 500);
	    
//	    redisTemplate.opsForHash().putAll("votes", map);
//	    Map<Object,Object> hashMap = redisTemplate.opsForHash().entries("votes");
//	    System.out.println(JSON.toJSONString(hashMap));
	    
//	    redisTemplate.opsForZSet().add("vote_count", 1, 0);
//	    redisTemplate.opsForZSet().add("vote_count", 2, 0);
//		redisTemplate.opsForZSet().add("vote_count", 3, 1);
//		redisTemplate.opsForZSet().add("vote_count", 4, 10);
//	    redisTemplate.opsForZSet().incrementScore("vote_count", 1, 1);
//	    System.out.println(redisTemplate.opsForZSet().score("vote_count", 1));
//	    System.out.println(redisTemplate.opsForZSet().score("vote_count", 2));
//	    Set<Object> values = redisTemplate.opsForZSet().reverseRangeByScore("vote_count", 0, Integer.MAX_VALUE, 0, 5);
//	    System.out.println(JSON.toJSONString(values));
	    
//	    Set<TypedTuple<Object>> values = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("vote_count", 0, Integer.MAX_VALUE, 0, 5);
//	    for(TypedTuple<Object> type:values){
//	    	System.out.println(type.getValue() +"+"+type.getScore());
//	    }
		
//		Set<TypedTuple<Object>> values = redisTemplate.opsForZSet().reverseRangeWithScores("vote_count", 1, 2);
//	    for(TypedTuple<Object> type:values){
//    	   System.out.println(type.getValue() +"+"+type.getScore());
//        }
//		redisTemplate.delete("votes");
//		Long x = redisTemplate.opsForList().leftPush("votes", "abc");
//		Long y = redisTemplate.opsForList().leftPush("votes", "def");
//		System.out.println("x:"+x+",y:"+y+","+redisTemplate.opsForList().rightPop("votes"));
		
//		Object o = redisTemplate.opsForValue().getAndSet("votes", "opt");
//		System.out.println("第一次:"+o);
//		o = redisTemplate.opsForValue().get("votes");
//		System.out.println("第二次:"+o);
		
//		System.out.println(redisTemplate.opsForZSet().zCard("VOTE_RANK"));
//		redisTemplate.opsForZSet().add("VOTE_RANK", 11L, 0L);
//		System.out.println(redisTemplate.opsForZSet().zCard("VOTE_RANK"));
		
//		redisTemplate.opsForValue().set("test", list);
//		list = (List<Integer>) redisTemplate.opsForValue().get("test");
//		System.out.println(JSON.toJSONString(list));
//		redisTemplate.delete("test");
	}
	
}
