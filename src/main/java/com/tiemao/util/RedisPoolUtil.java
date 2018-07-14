package com.tiemao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tiemao.common.RedisPool;

import redis.clients.jedis.Jedis;

/**
 * @author hlw
 * @date 2018年7月7日 17:05:11
 */
public class RedisPoolUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisPoolUtil.class);
	
	public static String set(String key, String value){
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.set(key, value);
		} catch (Exception e) {
			logger.error("set key:{},value:{} error",key,value,e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}
	/**
	 * 设置key的有效期,单位秒
	 * @param key
	 * @param exTime
	 * @return
	 */
	public static Long expire(String key, int exTime){
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.expire(key, exTime);
		} catch (Exception e) {
			logger.error("expire key:{} error",key,e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}
	public static String setEx(String key, String value, int exTime){
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.setex(key, exTime, value);
		} catch (Exception e) {
			logger.error("setEx key:{},value:{} error",key,value,e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}
	
	public static String get(String key){
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.get(key);
		} catch (Exception e) {
			logger.error("get key:{} error",key,e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}
	public static Long del(String key){
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.del(key);
		} catch (Exception e) {
			logger.error("del key:{} error",key,e);
			RedisPool.returnBrokenResource(jedis);
			return result;
		}
		RedisPool.returnResource(jedis);
		return result;
	}
	
	
}
