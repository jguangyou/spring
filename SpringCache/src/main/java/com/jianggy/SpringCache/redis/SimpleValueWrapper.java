package com.jianggy.SpringCache.redis;

import org.springframework.cache.Cache.ValueWrapper;

public class SimpleValueWrapper implements ValueWrapper {
	private Object value;

	public SimpleValueWrapper(Object value) {
		this.value = value;
	}

	@Override
	public Object get() {

		return this.value;
	}

}
