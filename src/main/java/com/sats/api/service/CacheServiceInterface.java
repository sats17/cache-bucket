package com.sats.api.service;


public interface CacheServiceInterface {
	
	void setTimeLimit(long timeLimit);

	void setCache(Object key,Object value);
	
	void setCacheWithTimeExpire(Object key,Object value);
	
	Object getCacheByKey(Object key);
	
	void cleanCache();
	
}
