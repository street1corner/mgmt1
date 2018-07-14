package com.tiemao.common;

import com.tiemao.util.PropertiesUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author hlw
 * @date 2018年7月7日 13:51:12
 */
public class RedisPool {

	//先加载出来
	private static JedisPool pool;//
	private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max,total", "20"));//最大连接数
	private static Integer maxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max,idle", "10"));//最大空闲jedis实例数
	private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min,idle", "2"));//最小空闲数
	private static boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.borrow", "true"));//在Borrow一个jedis实例,是否进行验证操作true,得到的实例肯定是可用的
	private static boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.return", "false"));//在Return一个jedis实例,是否进行验证操作true,返回的实例肯定是可用的
	private static String redisIp = PropertiesUtil.getProperty("redis.ip");
	private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));
	
	private static void initPool(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnReturn(testOnReturn);
		config.setBlockWhenExhausted(true);//连接耗尽时是否阻塞false抛出异常 默认true
		
		pool = new JedisPool(config, redisIp, redisPort, 1000*2);//超时2秒
		
	}
	
	static{
		initPool();
	}
	
	public static Jedis getJedis(){
		return pool.getResource();
	}
	
	public static void returnResource(Jedis jedis){
		pool.returnResource(jedis);
	}
	public static void returnBrokenResource(Jedis jedis){
		pool.returnBrokenResource(jedis);
	}
	
	
}
