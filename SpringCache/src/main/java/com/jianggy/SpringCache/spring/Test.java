package com.jianggy.SpringCache.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.cache.RedisCache;

import com.jianggy.SpringCache.noSpring.User;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = null;
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService service = (UserService) context.getBean("service");
		User user1 = service.getUserById("999");
		System.out.println("user1 ID:"+user1.getUserId());
		User user2 = service.getUserById("999");
		System.out.println("user2 ID:"+user2.getUserId());
	}

}
