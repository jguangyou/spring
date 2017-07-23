package com.jianggy.SpringCache.redis;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {
	private static final int MAX_TOTAL=500;
	private static final int MAX_IDLE=10;
	private static final int MAX_WAITMILLIS=10000;
	private static final boolean TEST_ON_BORROW=true;
	private static final String IP="127.0.0.1";
	private static final int PORT=6379;
	private static final int TIME_OUT=20000;
	private static JedisPool jedisPool = null;
	static{//执行初始化连接
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(MAX_TOTAL);
		config.setMaxIdle(MAX_IDLE);
		config.setMaxWaitMillis(MAX_WAITMILLIS);
		config.setTestOnBorrow(TEST_ON_BORROW);
		//......还有更多参数都可以添加
		
		jedisPool = new JedisPool(config, IP, PORT, TIME_OUT);
	}
	public static void main(String[] args) {
		Jedis jedis = jedisPool.getResource();
		jedis.set("test", "testdata");
		String res = jedis.get("test");
		System.out.println("获取的数据为："+res);
		//这一个一定要加上，否则连接池一会就用没了
		jedis.close();
		ListOperations v;
	}
	
}
