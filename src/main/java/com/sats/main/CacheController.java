package com.sats.main;

import com.sats.internal.model.Storage;
import com.sats.internal.service.CacheServiceImplementation;
import com.sats.internal.service.CacheServiceInterface;

import static com.sats.internal.config.Constants.CACHE_EXPIRY_TIME_LIMIT;

import java.util.LinkedHashMap;

public class CacheController {
	
	public CacheController(long timeLimit){
		cacheService.setTimeLimit(timeLimit);
	}
	
	public CacheController() {
		cacheService.setTimeLimit(CACHE_EXPIRY_TIME_LIMIT);
	}

	private CacheServiceInterface cacheService = new CacheServiceImplementation();
	
	public void setCache(String key,Object value) {
		cacheService.setCache(key, value);
	}
	
	public void setCache(String key,Object value,Boolean timeExpire) {
		if(timeExpire) {
			cacheService.setCacheWithTimeExpire(key, value);
		}
		else {
			cacheService.setCache(key, value);
		}
		
	}
	
	public Object getCache(String key) {
		return cacheService.getCacheByKey(key);
	}
	
	public LinkedHashMap<Object, Storage> getAll() {
		return cacheService.getAll();
	}
	
	public void clear() {
		cacheService.cleanCache();
	}
	
}
