package com.jianggy.SpringCache.noSpring;

public class UserDao {
	public User getUserById(String userId){
		User user = new User();
		user.setUserId(userId);
		return user;
	}
}
