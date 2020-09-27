package com.sats.caching.internal.services;

import java.util.HashMap;

import com.sats.caching.extern.CacheBucket;

/**
 * 
 * @version 1.0.0
 * @author sats17
 * 
 */
public class CacheController implements CacheBucket {
	
	
	/**
	 * This constructor creates the Cache Bucket with size and time limit.
	 * @param size : Total Number of cache entries can be store into Cache Bucket.
	 * @param timeLimit : Time limit in milliseconds, each key will expire automatically after this time limit. 
	 */
	public CacheController(int size,long timeLimit){
		cacheService.createCache(size,timeLimit);
	}
	
	/**
	 * This constructor creates the cache bucket with size.
	 * @param size : Total Number of cache entries can be store into Cache Bucket.
	 */
	public CacheController(int size) {
		cacheService.createCache(size);
	}

	/**
	 *  Object creation of cacheService class.
	 */
	private CacheServiceInterface cacheService = new CacheServiceImplementation();
	
	/**
	 * This method set cache into Cache Bucket.
	 * @param key : Unique cache key { Type: String}.
	 * @param value : Cache value that you want store into bucket.
	 * @return void : This method returns nothing.
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
	 * This method returns Cache from Cache bucket for given key.
	 * @param key : Unique key that present in cache bucket.
	 * @return Object : Cache Object. You need cast it with your cache type.
	 */
	public Object getCache(String key) {
		return cacheService.getCacheByKey(key);
	}
	
	/**
	 * This method returns all cache from Cache bucket.
	 * @return HashMap<String, Object> : Returns all keys and cache.
	 */
	public HashMap<String, Object> getAll() {
		return cacheService.getAll();
	}
	
	/**
	 * This method gives Cache bucket size.
	 * @return integer : Bucket size.
	 */
	@Override
	public int getBucketSize() {
		return cacheService.getBucketSize();
	}

	/**
	 * This method clears the cache for given key if it is presents in Cache bucket. 
	 * @param key : Unique cache key.
	 * @return void : Method returns nothing.
	 */
	public void clear(String key) {
		cacheService.clearCache(key);
	}
	
	/**
	 * This method Clear all cache from cache bucket.
	 * @return void : Method returns nothing.
	 */
	public void clear() {
		cacheService.clearCache();
	}

	/**
	 * This method returns the cache expire time limit for Cache Bucket.
	 * @return long : Return time in milliseconds.
	 */
	@Override
	public long getBucketTimeLimit() {
		return cacheService.getBucketTimeLimit();
	}

	/**
	 * To be implemented
	 * @return void: Method returns nothing.
	 */
	@Override
	public void setBucketTimeLimit() {
		
	}

	/**
	 * This method gives total number of cache entries present in Cache Bucket.
	 * @return integer : Returns count of total cache.
	 */
	@Override
	public int getTotalEntries() {
		return cacheService.getTotalEntries();
	}
	
}
