package com.github.sats17.cache.internal.services;

import java.util.Map;

/**
 * Interface of Cache Service layer
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
interface CacheServiceInterface {

	/**
	 * Creates cache bucket object with given capacity
	 * 
	 * @param bucketCapacity This capacity defines that this total number of cache
	 *                       can be store.
	 */
	void createBucket(int bucketCapacity);

	/**
	 * Creates cache bucket object with given size and time to live
	 * 
	 * @param bucketCapacity This capacity defines that this total number of cache
	 *                       can be store.
	 * @param timeToLive     Time to live in milliseconds, each cache will expire
	 *                       automatically after time to live value.
	 */
	void createBucket(int bucketCapacity, long timeToLive);

	/**
	 * Inserts Cache into bucket
	 * 
	 * @param key   Cache key
	 * @param value Cache value
	 */
	void setCache(String key, Object value);

	/**
	 * Gets cache from bucket for given key
	 * 
	 * @param key Cache key
	 */
	Object getCacheByKey(String key);

	/**
	 * Retrive all cache from bucket
	 * 
	 * @return {@link java.util.Map} contains each cache associated with key
	 */
	Map<String, Object> getAll();

	/**
	 * Clears the cache from bucket for matching key
	 * 
	 * @param key Unique cache key.
	 */
	void clearCache(String key);

	/**
	 * Wipe out all stored cache from bucket
	 */
	void clearCache();

	/**
	 * Gets bucket capacity
	 */
	int getBucketCapacity();

	/**
	 * Resize the bucket capacity
	 * 
	 * @param bucketCapacity new capacity
	 */
	void setBucketCapacity(int bucketCapacity);

	/**
	 * Gets total entries present in bucket
	 * 
	 * @return return total entries currently present in cache bucket
	 */
	int getTotalEntries();

	/**
	 * Gets TTL of bucket
	 * 
	 * @return Cache expiration time, which was set at cache bucket creation time
	 */
	long getBucketTTL();

	/**
	 * Update the TTL of bucket
	 * 
	 * @param timeToLive TTL value
	 */
	void setBucketTTL(long timeToLive);
}
