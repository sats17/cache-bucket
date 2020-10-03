package com.sats.caching.internal.services;

import java.util.Map;

import com.sats.caching.extern.CacheBucket;
import static com.sats.caching.internal.services.CommonValidation.*;

/**
 * 
 * @version 1.0.0
 * @author sats17
 * 
 */
public class CacheController implements CacheBucket {

	/**
	 * This constructor creates the Cache Bucket with size and time limit.
	 * 
	 * @param capacity   : Total Number of cache entries can be store into Cache
	 *                   Bucket.
	 * @param timeToLive : TTL in milliseconds, each cache present in bucket will
	 *                   expire automatically after given TTL.
	 */
	public CacheController(int capacity, long timeToLive) {
		validateCapacity(capacity);
		validateTTL(timeToLive);
		cacheService.createBucket(capacity, timeToLive);
	}

	/**
	 * This constructor creates the cache bucket with size.
	 * 
	 * @param capacity : Total Number of cache entries can be store into Cache
	 *                 Bucket.
	 */
	public CacheController(int capacity) {
		validateCapacity(capacity);
		cacheService.createBucket(capacity);
	}

	/**
	 * Object creation of cacheService class.
	 */
	private CacheServiceInterface cacheService = new CacheServiceImplementation();

	/**
	 * This method set cache into Cache Bucket.
	 * 
	 * @param key   : Unique cache key { Type: String}.
	 * @param value : Cache value that you want store into bucket.
	 * @return void : This method returns nothing.
	 */
	public void setCache(String key, Object value) {
		validateKey(key);
		validateValue(value);
		cacheService.setCache(key, value);
	}

	/**
	 * Set cache, there is different time limit for every object in cache. Currently
	 * not implemented
	 * 
	 * @param key
	 * @param value
	 * @param timeExpire
	 */
	public void setCache(String key, Object value, Boolean timeExpire) {
		if (timeExpire) {
			cacheService.setCacheWithTimeExpire(key, value);
		} else {
			cacheService.setCache(key, value);
		}

	}

	/**
	 * This method returns Cache from Cache bucket for given key.
	 * 
	 * @param key : Unique key that present in cache bucket.
	 * @return Object : Cache Object. You need cast it with your cache type.
	 */
	public Object getCache(String key) {
		validateKey(key);
		return cacheService.getCacheByKey(key);
	}

	/**
	 * This method returns all cache from Cache bucket.
	 * 
	 * @return HashMap<String, Object> : Returns all keys and cache.
	 */
	public Map<String, Object> getAll() {
		return cacheService.getAll();
	}

	/**
	 * This method gives Cache bucket size.
	 * 
	 * @return integer : Bucket size.
	 */
	@Override
	public int getBucketCapacity() {
		return cacheService.getBucketCapacity();
	}

	@Override
	public void setBucketCapacity(int capacity) {
		validateCapacity(capacity);
		cacheService.setBucketCapacity(capacity);
	}

	/**
	 * This method clears the cache for given key if it is presents in Cache bucket.
	 * 
	 * @param key : Unique cache key.
	 * @return void : Method returns nothing.
	 */
	public void clear(String key) {
		cacheService.clearCache(key);
	}

	/**
	 * This method Clear all cache from cache bucket.
	 * 
	 * @return void : Method returns nothing.
	 */
	public void clear() {
		cacheService.clearCache();
	}

	/**
	 * This method returns the cache expire time limit for Cache Bucket.
	 * 
	 * @return long : Return time in milliseconds.
	 */
	@Override
	public long getBucketTTL() {
		return cacheService.getBucketTTL();
	}

	/**
	 * Method Update the bucket TTL.
	 * 
	 * @param timeToLive: bucket TTL value.
	 * @return void: Method returns nothing.
	 */
	@Override
	public void setBucketTTL(long timeToLive) {
		validateTTL(timeToLive);
		cacheService.setBucketTTL(timeToLive);
	}

	/**
	 * This method gives total number of cache entries present in Cache Bucket.
	 * 
	 * @return integer : Returns count of total cache.
	 */
	@Override
	public int getTotalEntries() {
		return cacheService.getTotalEntries();
	}

}
