package com.jianggy.SpringCache.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.cache.Cache;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.cache.RedisCache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		JedisPool pool = (JedisPool) context.getBean("jedisPool");
		Jedis jedis = pool.getResource();
		jedis.select(1);
//		jedis.set("abc", "abcdsfdsfds");
		System.out.println(jedis.get("abc"));
		jedis.close();
		Cache cache;
		RedisCache cache2;
	}

}
