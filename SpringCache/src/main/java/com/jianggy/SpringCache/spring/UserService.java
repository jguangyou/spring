package com.jianggy.SpringCache.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jianggy.SpringCache.noSpring.User;
@Service("service")
public class UserService {
	@Autowired
	private UserDao userDao;
	@Cacheable(cacheNames="users")
	public User getUserById(String userId){
		User user = null;
		user = userDao.getUserById(userId);
		return user;
	}
}
