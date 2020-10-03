package com.sats.caching.extern;

import java.util.Map;

/**
 * @version 1.0.0
 * @author Sats17
 *
 */
public interface CacheBucket {

	/**
	 * This method set cache into Cache Bucket.
	 * @param key : Unique cache key { Type: String}.
	 * @param value : Cache value that you want store into bucket.
	 * @return void : This method returns nothing.
	 */
	public void setCache(String key,Object value);
	
	/**
	 * Not implemented
	 * @param key
	 * @param value
	 * @param canCacheExpire
	 */
	public void setCache(String key,Object value,Boolean canCacheExpire);
	
	/**
	 * This method returns Cache from Cache bucket for given key.
	 * @param key : Unique key that present in cache bucket.
	 * @return Object : Cache Object. You need cast it with your cache type.
	 */
	public Object getCache(String key);
	
	/**
	 * This method returns all cache from Cache bucket.
	 * @return HashMap<String, Object> : Returns all keys and cache.
	 */
	public Map<String, Object> getAll();
	
	/**
	 * This method gives Cache bucket size.
	 * @return integer : Bucket size.
	 */
	public int getBucketCapacity();
	
	/**
	 * This method clears the cache for given key if it is presents in Cache bucket. 
	 * @param key : Unique cache key.
	 * @return void : Method returns nothing.
	 */
	public void clear(String key);
	
	/**
	 * This method Clear all cache from cache bucket.
	 * @return void : Method returns nothing.
	 */
	public void clear();
	
	/**
	 * This method returns the cache time to live for Cache Bucket.
	 * @return long : Return time in milliseconds.
	 */
	public long getBucketTTL();
	
	/**
	 * To be implemented
	 * @return void: Method returns nothing.
	 */
	public void setBucketTTL();
	
	/**
	 * This method gives total number of cache entries present in Cache Bucket.
	 * @return integer : Returns count of total cache.
	 */
	public int getTotalEntries();
	
}
