package com.sats.internal.service;

import java.util.LinkedHashMap;

import com.sats.internal.model.Storage;

public interface CacheServiceInterface {
	
	void setTimeLimit(long timeLimit);

	void setCache(Object key,Object value);
	
	void setCacheWithTimeExpire(Object key,Object value);
	
	Object getCacheByKey(Object key);
	
	LinkedHashMap<Object, Storage> getAll();
	
	void deleteCacheByKey(Object key);
	
	void cleanCache();
	
}
