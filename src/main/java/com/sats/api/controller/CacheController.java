package com.sats.api.controller;

import com.sats.api.model.Cache;
import com.sats.api.service.CacheServiceImplementation;
import com.sats.api.service.CacheServiceInterface;

import static com.sats.api.config.Constants.CACHE_EXPIRY_TIME_LIMIT;

public class CacheController {
	
	public CacheController(long timeLimit){
		cacheService.setTimeLimit(timeLimit);
	}
	
	public CacheController() {
		cacheService.setTimeLimit(CACHE_EXPIRY_TIME_LIMIT);
	}

	private CacheServiceInterface cacheService = new CacheServiceImplementation();
	
	public void setCache(Object key,Object value) {
		cacheService.setCache(key, value);
	}
	
	public void setCache(Object key,Object value,Boolean timeExpire) {
		if(timeExpire) {
			cacheService.setCacheWithTimeExpire(key, value);
		}
		else {
			cacheService.setCache(key, value);
		}
		
	}
	
	public Object getCache(Object key) {
		return cacheService.getCacheByKey(key);
	}
	
	public void clear() {
		cacheService.cleanCache();
	}
	
}
