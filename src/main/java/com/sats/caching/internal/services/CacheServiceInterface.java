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
	 * @param size
	 */
	void createCache(int size);

	/**
	 * @param size
	 * @param timeLimit
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
	Object getCacheByKey(Object key);

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
	 * @param key
	 * @param value
	 */
	void setCacheWithTimeExpire(Object key, Object value);

}
