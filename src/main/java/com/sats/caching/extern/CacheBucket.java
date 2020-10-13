package com.sats.caching.extern;

import java.util.Map;

/**
 * Interface for cache bucket.
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
public interface CacheBucket {

	/**
	 * This method stores cache into Cache Bucket.
	 * 
	 * @param key   : Unique key for cache, key should not be null, blank and it's
	 *              size should be less than equal to 10.
	 * @param value : Cache value that can be any object, but should not be null.
	 * @return void : This method returns nothing.
	 * @throws IllegalArgumentException If key and value does not meets with given
	 *                                  conditions
	 */
	public void setCache(String key, Object value);

	/**
	 * This method returns cache object from cache bucket for matching key.
	 * 
	 * @param key : Unique cache key that present in cache bucket. Key should not be null, blank and it's
	 *              size should be less than equal to 10.
	 * @return Object : Cache Object.
	 * @throws IllegalArgumentException  If key does not meet given conditions
	 */
	public Object getCache(String key);

	/**
	 * This method returns all cache from Cache bucket.
	 * 
	 * @return Map<String, Object> : Map contains cache key and value.
	 */
	public Map<String, Object> getAll();

	/**
	 * This method returns bucket capacity.
	 * 
	 * @return int : bucket capacity.
	 */
	public int getBucketCapacity();

	/**
	 * This method resize the bucket capacity.
	 * 
	 * @param capacity : New capacity value, it should be between 1 to 200.
	 * @throws IllegalArgumentException if capacity does not meets required conditions.
	 */
	public void setBucketCapacity(int capacity);

	/**
	 * This method clears the cache for the given key, if it is presents in bucket.
	 * 
	 * @param key : Unique cache key, key should not be null, blank and it's
	 *              size should be less than equal to 10.
	 * @throws IllegalArgumentException if key does not meets required conditions.
	 */
	public void clear(String key);

	/**
	 * This method Clear all cache from bucket.
	 */
	public void clear();

	/**
	 * This method returns the cache time to live of the Bucket.
	 * 
	 * @return long : Return TTL time in milliseconds.
	 */
	public long getBucketTTL();

	/**
	 * Sets bucket time to live
	 * 
	 * @param timeToLive : Cache TTL should be in milliseconds, value should be between 1 to 14400000.
	 * @throws IllegalArgumentException if timeToLive does not meets required condition. 
	 */
	public void setBucketTTL(long timeToLive);

	/**
	 * This method returns total number of cache entries present in Bucket.
	 * 
	 * @return int : total count in integer for bucket.
	 */
	public int getTotalEntries();

}
