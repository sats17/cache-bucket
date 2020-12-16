package com.github.sats17.cache.internal.services;

import static com.github.sats17.cache.internal.services.CommonValidation.*;

import java.util.Map;

import com.github.sats17.cache.extern.CacheBucket;

/**
 * Provides implementation for CacheBucket
 * 
 * @version 1.0.0
 * @author Sats17
 * 
 */
public class BucketController implements CacheBucket {

	/**
	 * Constructor initialize the bucket with given capacity and TTL
	 * 
	 * @param capacity   Total Number of cache entries can be store into Cache
	 *                   Bucket, capacity should be between 1 to 200
	 * @param timeToLive Time to live in milliseconds, each of the cache present in
	 *                   bucket will expire automatically after given TTL timeToLive
	 *                   should be between 1 to 14400000
	 * @throws IllegalArgumentException when capacity and timeToLive does not meet
	 *                                  it's conditions
	 */
	public BucketController(int capacity, long timeToLive) {
		validateBucketCapacity(capacity);
		validateBucketTTL(timeToLive);
		cacheService.createBucket(capacity, timeToLive);
	}

	/**
	 * Constructor initialize the bucket with given capacity
	 * 
	 * @param capacity Total Number of cache entries can be store into Cache Bucket,
	 *                 capacity should be between 1 to 200
	 * @throws IllegalArgumentException when capacity does not meet it's conditions
	 */
	public BucketController(int capacity) {
		validateBucketCapacity(capacity);
		cacheService.createBucket(capacity);
	}

	/**
	 * Cache service implementation
	 */
	private CacheServiceInterface cacheService = new CacheServiceImplementation();

	/**
	 * Stores cache into bucket
	 * 
	 * @param key   Unique key for cache, key should not be null, blank and it's
	 *              size should be less than equal to 10
	 * @param value Cache value that can be any object, but should not be null
	 * @throws IllegalArgumentException If key and value does not meets with given
	 *                                  conditions
	 */
	public void setCache(String key, Object value) {
		validateKey(key);
		validateValue(value);
		cacheService.setCache(key, value);
	}

	/**
	 * Provides cache object from bucket for matching key
	 * <p>
	 * Note: Method returns cache value as java.lang.object type. You need to
	 * perform casting from type java.lang.Object to your cache value type, if you
	 * needed
	 * </p>
	 * 
	 * @param key Unique cache key that present in cache bucket. Key should not be
	 *            null, blank and it's size should be less than equal to 10.
	 * @return Cache value
	 * @throws IllegalArgumentException If key does not meet given conditions
	 */
	public Object getCache(String key) {
		validateKey(key);
		return cacheService.getCacheByKey(key);
	}

	/**
	 * Provides map contains key and cache value from bucket
	 * 
	 * <p>
	 * Note: Method returns cache value as java.lang.object type. You need to
	 * perform casting from type java.lang.Object to your cache value type, if you
	 * needed
	 * </p>
	 * 
	 * @return java.util.Map contains cache key and value
	 */
	public Map<String, Object> getAll() {
		return cacheService.getAll();
	}

	/**
	 * Provides bucket capacity
	 * 
	 * @return bucket capacity
	 */
	@Override
	public int getBucketCapacity() {
		return cacheService.getBucketCapacity();
	}

	/**
	 * Resize the bucket capacity
	 * 
	 * @param capacity New capacity value, it should be between 1 to 200
	 * @throws IllegalArgumentException if capacity does not meets required
	 *                                  conditions.
	 */
	@Override
	public void setBucketCapacity(int capacity) {
		validateBucketCapacity(capacity);
		cacheService.setBucketCapacity(capacity);
	}

	/**
	 * Clears the cache from bucket for the given key
	 * 
	 * @param key Unique cache key, key should not be null, blank and it's size
	 *            should be less than equal to 10.
	 * @throws IllegalArgumentException if key does not meets required conditions.
	 */
	public void clear(String key) {
		cacheService.clearCache(key);
	}

	/**
	 * Clears all cache from bucket
	 */
	public void clear() {
		cacheService.clearCache();
	}

	/**
	 * Provides the cache time to live value of the Bucket
	 * 
	 * @return Return TTL time in milliseconds
	 */
	@Override
	public long getBucketTTL() {
		return cacheService.getBucketTTL();
	}

	/**
	 * Sets bucket time to live
	 * 
	 * @param timeToLive Cache TTL should be in milliseconds, value should be
	 *                   between 1 to 14400000
	 * @throws IllegalArgumentException if timeToLive does not meets required
	 *                                  condition
	 */
	@Override
	public void setBucketTTL(long timeToLive) {
		validateBucketTTL(timeToLive);
		cacheService.setBucketTTL(timeToLive);
	}

	/**
	 * Returns total number of cache entries present in Bucket
	 * 
	 * @return total count of entries present in bucket
	 */
	@Override
	public int getTotalEntries() {
		return cacheService.getTotalEntries();
	}

	/**
	 * Provide information for bucket is empty is or not
	 * 
	 * @return true if bucket is empty, otherwise false
	 */
	@Override
	public boolean isEmpty() {
		return this.getTotalEntries() <= 0 ? true : false;
	}

}
