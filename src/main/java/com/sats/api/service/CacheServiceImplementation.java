package com.sats.api.service;

import java.util.LinkedHashMap;

import com.sats.api.model.Cache;
import com.sats.api.model.Storage;

public class CacheServiceImplementation implements CacheServiceInterface {

	private Cache<Object, Object> cache = new Cache<Object, Object>();
	
	public void setCache(Object key, Object value) {
		if(cache.getCacheSize() > 3) {
			cache.removeOldestElement();
			cache.setCache(key, value);
		}
		else {
			cache.setCache(key, value);
		}
	}

	public void setTimeLimit(long timeLimit) {
		cache.setTimeLimit(timeLimit);
	}

	public void setCacheWithTimeExpire(Object key, Object value) {
		//Under development
	}

	public Object getCacheByKey(Object key) {
		if(cache.getCache().containsKey(key)) {
			return cache.getCache().get(key);
		}
		else {
			return null;
		}
	}

	public void cleanCache() {
		cache.cleanCache();
	}
	
	
}
