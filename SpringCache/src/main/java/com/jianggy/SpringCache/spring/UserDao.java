package com.jianggy.SpringCache.spring;

import org.springframework.stereotype.Repository;

import com.jianggy.SpringCache.noSpring.User;
@Repository
public class UserDao {
	public User getUserById(String userId){
		User user = new User();
		user.setUserId(userId);
		System.out.println("从数据库获取user");
		return user;
	}
}
