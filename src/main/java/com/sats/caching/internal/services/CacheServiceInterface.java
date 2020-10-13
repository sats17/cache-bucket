package com.sats.caching.internal.services;

import java.util.Map;

/**
 * Interface of Cache Service layer.
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
interface CacheServiceInterface {

	/**
	 * Creates cache bucket object with given capacity.
	 * 
	 * @param bucketCapacity : This capacity defines that this total number of cache
	 *                       can be store.
	 */
	void createBucket(int bucketCapacity);

	/**
	 * Creates cache bucket object with given size and time to live.
	 * 
	 * @param bucketCapacity : This capacity defines that this total number of cache
	 *                       can be store.
	 * @param timeToLive      : Time to live in milliseconds, each cache will expire
	 *                       automatically after time to live value.
	 */
	void createBucket(int bucketCapacity, long timeToLive);

	/**
	 * Add Cache into bucket.
	 * 
	 * @param key : Unique key for cache.
	 * @param value : Actual cache object.
	 */
	void setCache(String key, Object value);

	/**
	 * Gets cache from bucket where key matches.
	 * 
	 * @param key : Unique key for cache.
	 * @param value : Actual cache object.
	 */
	Object getCacheByKey(String key);

	/**
	 * Gets all cache from bucket.
	 * 
	 * @return Map<String, Object> : Return Map contains each cache associated with key.
	 */
	Map<String, Object> getAll();

	/**
	 * Clears the cache from bucket for matching key.
	 * 
	 * @param key : Unique cache key.
	 */
	void clearCache(String key);

	/**
	 * Wipe out all stored cache from bucket.
	 */
	void clearCache();

	/**
	 * Gets bucket capacity.
	 */
	int getBucketCapacity();

	/**
	 * Resize the bucket capacity
	 * 
	 * @param bucketCapacity : new capacity.
	 */
	void setBucketCapacity(int bucketCapacity);

	/**
	 * Gets currently how many entries present in cache.
	 * 
	 * @return return total entries currently present in cache bucket.
	 */
	int getTotalEntries();

	/**
	 * Gets TTL of bucket.
	 * 
	 * @return Cache expiration time, which was set at cache bucket creation time.
	 */
	long getBucketTTL();

	/**
	 * Update the TTL of bucket.
	 * 
	 * @param timeToLive
	 */
	void setBucketTTL(long timeToLive);
}
