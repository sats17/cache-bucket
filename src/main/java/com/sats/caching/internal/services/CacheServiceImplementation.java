package com.sats.caching.internal.services;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0.0
 * @author sats17
 *
 */
class CacheServiceImplementation implements CacheServiceInterface {

	/**
	 * Declared cache object.
	 */
	private Cache<Object, Object> cache;

	/**
	 * This method set cache. First it check if cache is full or not, if it is full
	 * then it will remove oldest cache after that it will set new cache.
	 * 
	 * @param key
	 * @param value
	 * @return void
	 */
	public void setCache(Object key, Object value) {
		if (cache.getCacheSize() >= cache.getSize()) {
			cache.removeOldestCache();
			cache.setCache(key, value);
		} else {
			cache.setCache(key, value);
		}
	}

	/**
	 * This is cache initialization method, it will create space for cache and
	 * initialize it.
	 * 
	 * @param size
	 * @return void
	 */
	public void createCache(int size) {
		cache = new Cache<Object, Object>();
		cache.setSize(size);
	}

	/**
	 * This method is cache initialization method, it will create space for cache.
	 * cache object inside this method will expire after given time limit.
	 * 
	 * @param size
	 * @param timeLimit
	 */
	public void createCache(int size, long timeLimit) {
		cache = new Cache<Object, Object>(timeLimit);
		cache.setSize(size);
	}

	/**
	 * This method return cache for given key.
	 * 
	 * @param key
	 * @return cache
	 */
	public Object getCacheByKey(Object key) {
		if (cache.getCache().containsKey(key)) {
			return cache.getCache().get(key);
		} else {
			return null;
		}
	}

	/**
	 * This method clear all cache.
	 */
	public void clearCache() {
		cache.clear();
	}

	/**
	 * This method returns all object store in cache.
	 * 
	 * @return ConcurrentHashMap<Object, Storage>
	 */
	public HashMap<Object, Object> getAll() {
		return cache.getCache(); 
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

}
