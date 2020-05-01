package com.sats.internal.service;

import java.util.concurrent.ConcurrentHashMap;

import com.sats.internal.model.Storage;

/**
 * @version 1.0.0
 * @author sats17
 *
 */
public interface CacheServiceInterface {

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
	ConcurrentHashMap<Object, Storage> getAll();

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
