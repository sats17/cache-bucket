package com.sats.caching.internal.services;

import java.util.HashMap;

import com.sats.caching.extern.CacheBucket;

/**
 * @version 1.0.0
 * @author sats17
 * 
 */
public class CacheController implements CacheBucket {
	
	
	/**
	 * This constructor create cache object.
	 * @param size : Total Number of cache that can store.
	 * @param timeLimit : Time limit in milliseconds, each key will expire automatically after this time limit. 
	 */
	public CacheController(int size,long timeLimit){
		cacheService.createCache(size,timeLimit);
	}
	
	/**
	 * This constructor create cache object.
	 * @param size : Total Number of cache that can store.
	 */
	public CacheController(int size) {
		cacheService.createCache(size);
	}

	/**
	 *  Object creation of cacheService class.
	 */
	private CacheServiceInterface cacheService = new CacheServiceImplementation();
	
	/**
	 * This method set cache.
	 * @param key : Unique cache key { Type: String}.
	 * @param value : Cache value that you want store into bucket.
	 * @return void
	 */
	public void setCache(String key,Object value) {
		cacheService.setCache(key, value);
	}
	
	/**
	 * Set cache, there is different time limit for every object in cache.
	 * Currently not implemented
	 * @param key
	 * @param value
	 * @param timeExpire
	 */
	public void setCache(String key,Object value,Boolean timeExpire) {
		if(timeExpire) {
			cacheService.setCacheWithTimeExpire(key, value);
		}
		else {
			cacheService.setCache(key, value);
		}
		
	}
	
	/**
	 * This method get cache by cache name.
	 * @param key
	 * @return CacheObject
	 */
	public Object getCache(String key) {
		return cacheService.getCacheByKey(key);
	}
	
	/**
	 * This method return concurrent hashmap of all stored cache.
	 * @return ConcurrentHashMap<Object, Storage>
	 */
	public HashMap<Object, Object> getAll() {
		return cacheService.getAll();
	}
	
	/**
	 * Clear cache by cache key.
	 * @param key
	 * @return void
	 */
	public void clear(String key) {
		cacheService.clearCache(key);
	}
	
	/**
	 * Clear all present cache.
	 * @return void
	 */
	public void clear() {
		cacheService.clearCache();
	}

	@Override
	public int getBucketSize() {
		return cacheService.getBucketSize();
	}

	@Override
	public long getBucketTimeLimit() {
		return cacheService.getBucketTimeLimit();
	}

	@Override
	public void setBucketTimeLimit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotalEntries() {
		return cacheService.getTotalEntries();
	}
	
}
