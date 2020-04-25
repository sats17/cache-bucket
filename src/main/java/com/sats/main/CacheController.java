package com.sats.main;

import com.sats.internal.model.Storage;
import com.sats.internal.service.CacheServiceImplementation;
import com.sats.internal.service.CacheServiceInterface;
import java.util.concurrent.ConcurrentHashMap;

public class CacheController {
	
	public CacheController(long timeLimit){
		cacheService.createCacheTimeExpire(timeLimit);
	}
	
	public CacheController() {
		cacheService.createCache();
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
	
	public ConcurrentHashMap<Object, Storage> getAll() {
		return cacheService.getAll();
	}
	
	public void clear() {
		cacheService.cleanCache();
	}
	
}
