package com.github.sats17.cache.extern;

import java.util.Map;

/**
 * Interface of cache bucket
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
public interface CacheBucket {

	/**
	 * Stores cache into Bucket with key and value
	 * 
	 * @param key Unique key for cache, key should not be null, blank and it's
	 *              size should be less than equal to 10
	 * @param value Cache value that can be any object, but should not be null
	 * @throws IllegalArgumentException If key and value does not meets with given
	 *                                  conditions
	 */
	public void setCache(String key, Object value);

	/**
	 * Returns cache object from bucket with matching key
	 * 
	 * @param key Unique cache key that present in cache bucket. Key should not be null, blank and it's
	 *              size should be less than equal to 10
	 * @return the cache value
	 * @throws IllegalArgumentException  If key does not meets given conditions
	 */
	public Object getCache(String key);

	/**
	 * Returns all cache and it's related keys from bucket
	 * 
	 * @return {@link java.util.Map<String, Object>} contains cache key and value
	 */
	public Map<String, Object> getAll();

	/**
	 * Returns bucket capacity
	 * 
	 * @return bucket capacity
	 */
	public int getBucketCapacity();

	/**
	 * Resize the bucket capacity
	 * 
	 * @param capacity New capacity value, it should be between 1 to 200.
	 * @throws IllegalArgumentException if capacity does not meets required conditions.
	 */
	public void setBucketCapacity(int capacity);

	/**
	 * Clears the cache for the given key
	 * 
	 * @param key Unique cache key, key should not be null, blank and it's
	 *              size should be less than equal to 10.
	 * @throws IllegalArgumentException if key does not meets required conditions.
	 */
	public void clear(String key);

	/**
	 * Clears the all cache that present in bucket
	 */
	public void clear();

	/**
	 * Returns the cache time to live of the Bucket
	 * 
	 * @return TTL time in milliseconds
	 */
	public long getBucketTTL();

	/**
	 * Sets the bucket time to live
	 * 
	 * @param timeToLive Cache TTL should be in milliseconds, value should be between 1 to 14400000.
	 * @throws IllegalArgumentException if timeToLive does not meets required condition. 
	 */
	public void setBucketTTL(long timeToLive);

	/**
	 * Provides total count of cache entries present in bucket
	 * 
	 * @return total entries
	 */
	public int getTotalEntries();
	
	/**
	 * Provide information for bucket is empty is or not
	 * 
	 * @return true if bucket is empty, otherwise false
	 */
	public boolean isEmpty();

}
