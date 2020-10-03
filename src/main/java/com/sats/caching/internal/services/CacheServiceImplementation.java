package com.sats.caching.internal.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @version 1.0.0
 * @author sats17
 *
 */
class CacheServiceImplementation implements CacheServiceInterface {

	/**
	 * Declared cache object.
	 */
	private Bucket<Object, Object> bucket;

	/**
	 * This is cache initialization method, it will create space for cache and
	 * initialize it.
	 * 
	 * @param bucketCapacity
	 * @return void
	 */
	public void createBucket(int bucketCapacity) {
		bucket = new Bucket<>();
		bucket.setBucketCapacity(bucketCapacity); 
	}

	/**
	 * This method is cache initialization method, it will create space for cache.
	 * cache object inside this method will expire after given time limit.
	 * 
	 * @param bucketCapacity
	 * @param timeLimit
	 */
	public void createBucket(int bucketCapacity, long timeToLive) {
		bucket = new Bucket<>(timeToLive);
		bucket.setBucketCapacity(bucketCapacity);
	}
	
	/**
	 * This method set cache. First it check if cache is full or not, if it is full
	 * then it will remove oldest cache after that it will set new cache.
	 * 
	 * @param key
	 * @param value
	 * @return void
	 */
	public void setCache(Object key, Object value) {
		if (bucket.getTotalEntries() >= bucket.getBucketCapacity()) {
			bucket.removeOldestCache();
			bucket.setCache(key, value);
		} else {
			bucket.setCache(key, value);
		}
	}

	/**
	 * This method return cache for given key.
	 * 
	 * @param key
	 * @return cache
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
	 * This method returns all object store in cache.
	 * 
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getAll() {
		Map<String, Object> returningObject = new HashMap<>();
		for (Entry<Object, CacheEntries> entry : bucket.getCache().entrySet()) {
		    String key = entry.getKey().toString();
		    Object value = entry.getValue().getValue();
		    returningObject.put(key, value);
		}
		return returningObject;
	}

	/**
	 * This method clear all cache.
	 */
	public void clearCache() {
		bucket.clear();
	}

	/**
	 * This method clear cache for given key.
	 * 
	 * @param key
	 */
	public void clearCache(Object key) {
		bucket.clear(key);
	}

	/**
	 * Currently in development
	 * 
	 * @param key
	 * @param value
	 */
	public void setCacheWithTimeExpire(Object key, Object value) {
		// Under development
	}

	/**
	 * Method return cache size.
	 */
	@Override
	public int getBucketCapacity() {
		return bucket.getBucketCapacity();
	}
	
	/**
	 * Method sets new bucket capacity. If we want to shrink bucket capacity, then dynamically method will
	 * remove oldest cache from bucket.
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
			bucket.setBucketCapacity(bucketCapacity);
		}
	}

	/**
	 * Method returns bucket TTL.
	 * @return timeToLive
	 */
	@Override
	public long getBucketTTL() {
		return bucket.getTimeToLive();
	}

	/**
	 * Method set bucket TTL.
	 * @param timeToLive : bucket TTL.
	 */
	@Override
	public void setBucketTTL(long timeToLive) {
		bucket.setTimeToLive(timeToLive);
	}

	/**
	 * Method return total entries present in bucket.
	 * @return total entries present in bucket.
	 */
	@Override
	public int getTotalEntries() {
		return bucket.getTotalEntries();
	}

}
