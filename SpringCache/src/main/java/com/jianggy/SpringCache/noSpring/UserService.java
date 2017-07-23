package com.jianggy.SpringCache.noSpring;

public class UserService {
	private CacheManager<User> manager;
	UserDao userDao;
	
	public UserService() {
		manager = new CacheManager<>();
		userDao = new UserDao();
	}
	/**
	 * 模拟获取用户
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId){
		//首先从缓存中获取
		User user = manager.getValue(userId);
		if (user != null) {
			System.out.println("get from cache..."+userId);
			return user;
		//如果缓存中没有数据就从数据库中获取
		}else {
			user =  userDao.getUserById("111");
			System.out.println("get from DB..."+userId);
			if (user!=null) {
				//将获取的数据放入缓存中
				manager.addOrUpdateKey(user.getUserId(), user);
			}
			return user;
		}
	}
}
