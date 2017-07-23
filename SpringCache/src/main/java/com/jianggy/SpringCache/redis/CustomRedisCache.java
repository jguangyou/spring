package com.jianggy.SpringCache.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Callable;
import java.util.jar.Attributes.Name;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CustomRedisCache implements Cache {
	private String name;
	private JedisPool pool;

	public CustomRedisCache() {
		RedisCache redisCache;
		// TODO Auto-generated constructor stub
	}

	public CustomRedisCache(String name, JedisPool pool) {
		this.name = name;
		this.pool = pool;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getNativeCache() {
		return pool;
	}

	@Override
	public ValueWrapper get(Object key) {
		System.out.println("从redis中获取数据");
		Object object = this.get(key, Object.class);
		return object != null ? new SimpleValueWrapper(object) : null;
	}

	@Override
	public <T> T get(Object key, Class<T> type) {
		Jedis jedis = pool.getResource();
		String keyString = (String) key;
		byte[] bytes = jedis.get(keyString.getBytes());
		jedis.close();
		return bytes != null ? bytes2Object(bytes) : null;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		Jedis jedis = pool.getResource();
		String keyString = (String) key;
		byte[] bytes = jedis.get(keyString.getBytes());
		System.out.println(new String(bytes));
		jedis.close();
		return bytes2Object(bytes);
	}

	@Override
	public void put(Object key, Object value) {
		System.out.println("写redis===============");
		Jedis jedis = pool.getResource();
		jedis.set(((String) key).getBytes(), object2Bytes(value));
		jedis.close();
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		return null;
	}

	@Override
	public void evict(Object key) {
		Jedis jedis = pool.getResource();
		String keyString = (String) key;
		jedis.del(keyString.getBytes());
		jedis.close();
	}

	@Override
	public void clear() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public JedisPool getPool() {
		return pool;
	}

	public void setPool(JedisPool pool) {
		this.pool = pool;
	}

	private <T> byte[] object2Bytes(T t) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(t);
			objectOutputStream.flush();
			bytes = outputStream.toByteArray();
			outputStream.close();
			objectOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	private <T> T bytes2Object(byte[] bytes) {
		T res = null;
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			res = (T) objectInputStream.readObject();
			inputStream.close();
			objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

}
