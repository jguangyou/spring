package com.jianggy.SpringCache.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisTemplateTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		RedisTemplate<String, ? extends Object> template = (RedisTemplate<String, ? extends Object>) context.getBean("redisTemplate");
		//使用Template写操作
		template.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set("templateTest".getBytes(), "templateTest".getBytes());
				return true;
			}
			
		});
		//使用Template读操作
		template.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				String res = new String(connection.get("templateTest".getBytes()));
				System.out.println("获取的结果为："+res);
				return res;
			}
		});
		
		ValueOperations<String, String> vOperations = (ValueOperations<String, String>) template.opsForValue();
		vOperations.set("operationTest", "operationTest");
		System.out.println("读取结果为："+vOperations.get("operationTest"));
		
		ValueOperations<String, Object> valueOperations = (ValueOperations<String, Object>) template.opsForValue();
		Person person = new Person();
		person.setAge(11);
		person.setName("sfdsgdsfdsafdsa");
		valueOperations.set("testObject", person);
		
		Person person2 = (Person) valueOperations.get("testObject");
		System.out.println(person2.getName());
	}

}
