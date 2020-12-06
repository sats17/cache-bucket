package com.github.sats17.cache.internal.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Implementation of CacheServiceInterface.
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
class CacheServiceImplementation implements CacheServiceInterface {

	/**
	 * Bucket object.
	 */
	private Bucket bucket;

	/**
	 * This is bucket creation method, it will create bucket having spaces for cache that we can store.
	 * 
	 * @param bucketCapacity : capacity of bucket.
	 */
	public void createBucket(int bucketCapacity) {
		bucket = new Bucket(bucketCapacity);
	}

	/**
	 * This is bucket creation method, it will create bucket having spaces for cache that we can store.
	 * 
	 * @param bucketCapacity : capacity of bucket.
	 * @param timeToLive : time to live each cache presents in bucket.
	 */
	public void createBucket(int bucketCapacity, long timeToLive) {
		bucket = new Bucket(bucketCapacity, timeToLive);
	}
	
	/**
	 * Method add cache into bucket. If bucket is full then method will remove old cache and add new one.
	 * 
	 * @param key : Unique key for cache.
	 * @param value : Cache object.
	 */
	public void setCache(String key, Object value) {
		if (bucket.getTotalEntries() >= bucket.getBucketCapacity()) {
			bucket.removeOldestCache();
			bucket.setCache(key, value);
		} else {
			bucket.setCache(key, value);
		}
	}

	/**
	 * This method return cache value for matching key. If none of key match then it will return null.
	 * 
	 * @param key : Cache key.
	 * @return Object : Cache value
	 */
	public Object getCacheByKey(String key) {
		if (bucket.getCache().containsKey(key)) {
			CacheEntries storage = bucket.getCache(key);
			return storage.getValue();
		} else {
			return null;
		}
	}
	
	/**
	 * This method returns all cache from bucket, for security purpose method fetches cache values and keys
	 * from object and stores into in HashMap. 
	 * 
	 * @return Map<String, Object> : Contains all data about cache.
	 */
	public Map<String, Object> getAll() {
		Map<String, Object> returningObject = new HashMap<>();
		for (Entry<String, CacheEntries> entry : bucket.getCache().entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue().getValue();
		    returningObject.put(key, value);
		}
		return returningObject;
	}

	/**
	 * This method clears the all cache.
	 */
	public void clearCache() {
		bucket.clear();
	}

	/**
	 * This method clears the cache for given key.
	 * 
	 * @param key : Cache key
	 */
	public void clearCache(String key) {
		bucket.clear(key);
	}

	/**
	 * Method return bucket capacity.
	 */
	@Override
	public int getBucketCapacity() {
		return bucket.getBucketCapacity();
	}
	
	/**
	 * Method sets new bucket capacity. If we want to shrink bucket capacity, then dynamically method will
	 * remove oldest cache from bucket.
	 * 
	 * @param bucketCapacity : New bucket capacity.
	 */
	@Override
	public void setBucketCapacity(int bucketCapacity) {
		if(bucketCapacity > bucket.getBucketCapacity()) {
			bucket.setBucketCapacity(bucketCapacity);
		} else {
			int diff = bucket.getTotalEntries() - bucketCapacity;
			for(int i = 0; i < diff; i++) {
				bucket.removeOldestCache();
			}
			bucket.shrinkBucket(bucketCapacity);
		}
	}

	/**
	 * Method returns bucket TTL value.
	 * 
	 * @return timeToLive : time to live value of bucket.
	 */
	@Override
	public long getBucketTTL() {
		return bucket.getTimeToLive();
	}

	/**
	 * Method set bucket TTL value.
	 * 
	 * @param timeToLive : bucket TTL.
	 */
	@Override
	public void setBucketTTL(long timeToLive) {
		bucket.setTimeToLive(timeToLive);
	}

	/**
	 * Method return total cache entries present in bucket.
	 * 
	 * @return int : total entries present in bucket.
	 */
	@Override
	public int getTotalEntries() {
		return bucket.getTotalEntries();
	}

}
