package com.sats.caching.internal.services;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0.0
 * @author sats17
 *
 */
interface CacheServiceInterface {

	/**
	 * Creates cache object with given size.
	 * @param size : This size defines that this total number of cache can be store.
	 */
	void createCache(int size);

	/**
	 * Creates cache object with given size and time limit.
	 * @param size : This size defines that this total number of cache can be store.
	 * @param timeLimit : Time limit in milliseconds, each key will expire automatically after this time limit. 
	 */
	void createCache(int size, long timeLimit);

	/**
	 * @param key
	 * @param value
	 */
	void setCache(Object key, Object value);

	/**
	 * @param key
	 * @return Object
	 */
	Object getCacheByKey(String key);

	/**
	 * @return
	 */
	HashMap<Object, Object> getAll();

	/**
	 * @param key
	 */
	void clearCache(Object key);

	/**
	 * 
	 */
	void clearCache();

	/**
	 * Future implementation, where each cache can have their own time expiration
	 * @param key
	 * @param value
	 */
	void setCacheWithTimeExpire(Object key, Object value);
	
	/**
	 * returns total cache bucket size.
	 */
	int getBucketSize();
	
	/**
	 * 
	 * @return return total entries currently present in cache bucket.
	 */
	int getTotalEntries();
	
	/**
	 * 
	 * @return Cache expiration time, which was set at cache bucket creation time. 
	 */
	long getBucketTimeLimit();
	
	void setBucketTimeLimit();
}
