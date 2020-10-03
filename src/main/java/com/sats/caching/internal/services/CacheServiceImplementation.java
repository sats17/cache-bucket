package com.sats.caching.internal.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @version 1.0.0
 * @author sats17
 *
 */
class CacheServiceImplementation implements CacheServiceInterface {

	/**
	 * Declared cache object.
	 */
	private Bucket<Object, Object> cache;

	/**
	 * This is cache initialization method, it will create space for cache and
	 * initialize it.
	 * 
	 * @param bucketCapacity
	 * @return void
	 */
	public void createBucket(int bucketCapacity) {
		cache = new Bucket<>();
		cache.setBucketCapacity(bucketCapacity); 
	}

	/**
	 * This method is cache initialization method, it will create space for cache.
	 * cache object inside this method will expire after given time limit.
	 * 
	 * @param bucketCapacity
	 * @param timeLimit
	 */
	public void createBucket(int bucketCapacity, long timeToLive) {
		cache = new Bucket<>(timeToLive);
		cache.setBucketCapacity(bucketCapacity);
	}
	
	/**
	 * This method set cache. First it check if cache is full or not, if it is full
	 * then it will remove oldest cache after that it will set new cache.
	 * 
	 * @param key
	 * @param value
	 * @return void
	 */
	public void setCache(Object key, Object value) {
		if (cache.getTotalEntries() >= cache.getBucketCapacity()) {
			cache.removeOldestCache();
			cache.setCache(key, value);
		} else {
			cache.setCache(key, value);
		}
	}

	/**
	 * This method return cache for given key.
	 * 
	 * @param key
	 * @return cache
	 */
	public Object getCacheByKey(String key) {
		if (cache.getCache().containsKey(key)) {
			CacheEntries storage = cache.getCache(key);
			return storage.getValue();
		} else {
			return null;
		}
	}
	
	/**
	 * This method returns all object store in cache.
	 * 
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getAll() {
		Map<String, Object> returningObject = new HashMap<>();
		for (Entry<Object, CacheEntries> entry : cache.getCache().entrySet()) {
		    String key = entry.getKey().toString();
		    CacheEntries value = entry.getValue();
		    returningObject.put(key, value);
		}
		return returningObject;
	}

	/**
	 * This method clear all cache.
	 */
	public void clearCache() {
		cache.clear();
	}

	/**
	 * This method clear cache for given key.
	 * 
	 * @param key
	 */
	public void clearCache(Object key) {
		cache.clear(key);
	}

	/**
	 * Currently in development
	 * 
	 * @param key
	 * @param value
	 */
	public void setCacheWithTimeExpire(Object key, Object value) {
		// Under development
	}

	/**
	 * Method return cache size.
	 */
	@Override
	public int getBucketCapacity() {
		return cache.getBucketCapacity();
	}

	@Override
	public long getBucketTTL() {
		return cache.getTimeToLive();
	}

	@Override
	public void setBucketTTL() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotalEntries() {
		return cache.getTotalEntries();
	}

}
