package com.sats.internal.service;

import java.util.concurrent.ConcurrentHashMap;
import com.sats.internal.model.Cache;
import com.sats.internal.model.Storage;

public class CacheServiceImplementation implements CacheServiceInterface {

	private Cache<Object, Object> cache;
	
	public void setCache(Object key, Object value) {
		if(cache.getCacheSize() >= cache.getSize()) {
			cache.removeOldestCache();
			cache.setCache(key, value);
		}
		else {
			cache.setCache(key, value);
		}
	}

	
	public void createCache(int size) {
		cache = new Cache<Object, Object>();
		cache.setSize(size);
	}
	
	public void createCache(int size,long timeLimit) {
		cache =  new Cache<Object, Object>(timeLimit);
		cache.setSize(size);
	}

	public Object getCacheByKey(Object key) {
		if(cache.getCache().containsKey(key)) {
			return cache.getCache().get(key);
		}
		else {
			return null;
		}
	}

	public void clearCache() {
		cache.clear();
	}

	public ConcurrentHashMap<Object, Storage> getAll() {
		return cache.getCache();
	}

	public void clearCache(Object key) {
		cache.clear(key);
	}

	public void setCacheWithTimeExpire(Object key, Object value) {
		//Under development
	}
	
	
	
}
