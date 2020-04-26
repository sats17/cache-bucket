package com.sats.internal.service;

import java.util.concurrent.ConcurrentHashMap;

import com.sats.internal.model.Storage;

public interface CacheServiceInterface {
	
	void createCache(int size);
	
	void createCache(int size,long timeLimit);

	void setCache(Object key,Object value);
	
	Object getCacheByKey(Object key);
	
	ConcurrentHashMap<Object, Storage> getAll();
	
	void clearCache(Object key);
	
	void clearCache();
	
	void setCacheWithTimeExpire(Object key,Object value);
	
}
