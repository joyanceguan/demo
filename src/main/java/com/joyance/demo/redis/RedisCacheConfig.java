package com.joyance.demo.redis;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisCommands;

/**
 * 通过spring管理redis缓存配置
 * 
 * @author Administrator
 *
 */
@Configuration  
@EnableCaching 
public class RedisCacheConfig extends CachingConfigurerSupport {
	
    private static final long LOCK_EXPIRE = 0;
	private volatile JedisConnectionFactory jedisConnectionFactory;
    private volatile RedisTemplate<String, Object> redisTemplate;
    private volatile RedisCacheManager redisCacheManager;

    public RedisCacheConfig() {
        super();
    }

    /**
     * 带参数的构造方法 初始化所有的成员变量
     * 
     * @param jedisConnectionFactory
     * @param redisTemplate
     * @param redisCacheManager
     */
    public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String, Object> redisTemplate,
            RedisCacheManager redisCacheManager) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.redisTemplate = redisTemplate;
        this.redisCacheManager = redisCacheManager;
    }

    public JedisConnectionFactory getJedisConnecionFactory() {
        return jedisConnectionFactory;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public RedisCacheManager getRedisCacheManager() {
        return redisCacheManager;
    }

    @Bean
    public KeyGenerator customKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
    
//    @Override public boolean lock(String key, long expire, int retryTimes, long sleepMillis) { 
//    	boolean result = setRedis(key, expire); // 如果获取锁失败，按照传入的重试次数进行重试 while((!result) && retryTimes-- > 0){ try {
//          logger.debug("lock failed, retrying..." + retryTimes);
//    Thread.sleep(sleepMillis);
//} catch (InterruptedException e) { return false;
//}
//result = setRedis(key, expire);
//} return result;
//} 
//
//private boolean setRedis(String key, long expire) { 
//	try { 
//		String result = redisTemplate.execute(new RedisCallback<String>() {
//           @Override 
//           public String doInRedis(RedisConnection connection) throws DataAccessException {
//            JedisCommands commands = (JedisCommands) connection.getNativeConnection(); 
//            String uuid = UUID.randomUUID().toString();
//             lockFlag.set(uuid); 
//             return commands.set(key, uuid, "NX", "PX", expire);
//    }
//}); return !StringUtils.isEmpty(result);
//} catch (Exception e) {
//logger.error("set redis occured an exception", e);
//} return false;
//}

     public void x(String key,long expire){
    	 redisTemplate.execute(new RedisCallback<Boolean>(){

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				JedisCommands commands = (JedisCommands) connection.getNativeConnection();
				String str = commands.set(key, "1", "NX", "PX", expire);
				return !StringUtils.isBlank(str);
			}});
     }
    
}
