package com.jianggy.SpringCache.noSpring;

import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

public class Test {
	public static void main(String[] args) {
		UserService service = new UserService();
		System.out.println("第一次获取User");
		service.getUserById("111");
		System.out.println("第二次获取User");
		service.getUserById("111");
		System.out.println("第三次获取User");
		service.getUserById("111");
		System.out.println("版本v2.0");
		service.getUserById("111");
	}
}
