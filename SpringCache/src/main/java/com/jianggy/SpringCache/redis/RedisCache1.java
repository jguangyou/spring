package com.jianggy.SpringCache.redis;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.Callable;

import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCache1 extends AbstractValueAdaptingCache {
	RedisTemplate<String, Object> redisTemplate;
	
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	protected RedisCache1(boolean allowNullValues) {
		super(allowNullValues);
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Object getNativeCache() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		byte[] bytes = getValue((String)key);
		Object object = bytes2Object(bytes);
		return null;
	}

	@Override
	public void put(Object key, Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evict(Object key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Object lookup(Object key) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private byte[] getValue(String key){
		byte[] bytes = redisTemplate.execute(new RedisCallback<byte[]>() {

			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] res = connection.get(key.getBytes());
				return res;
			}
			
		});
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
