package com.jianggy.SpringCache.spring;

import org.springframework.beans.factory.FactoryBean;

public class CustomerCacheFactoryBean<T> implements FactoryBean<T> {

	@Override
	public T getObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
