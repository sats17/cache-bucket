package com.sats.internal.service;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.sats.internal.model.Cache;
import com.sats.internal.model.Storage;

public class CacheServiceImplementation implements CacheServiceInterface {

	private Cache<Object, Object> cache;
	
	public void setCache(Object key, Object value) {
		if(cache.getCacheSize() > 3) {
			cache.removeOldestElement();
			cache.setCache(key, value);
		}
		else {
			cache.setCache(key, value);
		}
	}

	
	public void createCache() {
		cache = new Cache<Object, Object>();
	}
	
	public void createCacheTimeExpire(long timeLimit) {
		cache =  new Cache<Object, Object>(timeLimit);
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

	public ConcurrentHashMap<Object, Storage> getAll() {
		return cache.getCache();
	}

	public void deleteCacheByKey(Object key) {}


	
	
	
}
