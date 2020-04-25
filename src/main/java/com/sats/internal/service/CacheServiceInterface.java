package com.sats.internal.service;

import java.util.concurrent.ConcurrentHashMap;

import com.sats.internal.model.Storage;

public interface CacheServiceInterface {
	
	void createCache();
	
	void createCacheTimeExpire(long timeLimit);

	void setCache(Object key,Object value);
	
	void setCacheWithTimeExpire(Object key,Object value);
	
	Object getCacheByKey(Object key);
	
	ConcurrentHashMap<Object, Storage> getAll();
	
	void deleteCacheByKey(Object key);
	
	void cleanCache();
	
}
