package com.github.sats17.cache.internal.services;

import static com.github.sats17.cache.internal.services.Constants.SCHEDULAR_INTIAL_DELAY;
import static com.github.sats17.cache.internal.services.Constants.SCHEDULAR_PERIOD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Contains all methods related to bucket
 * 
 * @version 1.0.0
 * @author Sats17
 */
class Bucket {

	/**
	 * Bucket TTL variable
	 */
	private long timeToLive = -1;

	/**
	 * Bucket capacity variable
	 */
	private int bucketCapacity = -1;

	/**
	 * Indexing for bucket, contains all cache keys
	 */
	private List<String> index;

	/**
	 * Cache bucket
	 * 
	 * @see java.util.concurrent.ConcurrentMap
	 */
	private ConcurrentMap<String, CacheEntries> cache;

	@SuppressWarnings("unused")
	private Bucket() {
	}

	/**
	 * Bucket constructor, initialize the bucket having given bucketCapacity
	 * 
	 * @param bucketCapacity Bucket capacity
	 */
	public Bucket(int bucketCapacity) {
		this.setIndex(new ArrayList<>(bucketCapacity));
		this.cache = new ConcurrentHashMap<>(bucketCapacity);
		this.bucketCapacity = bucketCapacity;
	}

	/**
	 * Parameterized constructor, Initialize the bucket with bucketCapacity and TTL
	 * 
	 * @param bucketCapacity Bucket capacity
	 * @param timeToLive     TTL value
	 */
	public Bucket(int bucketCapacity, long timeToLive) {
		this.setIndex(new ArrayList<>(bucketCapacity));
		this.cache = new ConcurrentHashMap<>(bucketCapacity);
		this.bucketCapacity = bucketCapacity;
		this.timeToLive = timeToLive;
		initializeScheduler();
	}

	/**
	 * Runs the scheduler and checks if cache is empty or not for every seconds. if
	 * cache is not empty then it will call cacheAutoClear method.
	 */
	private void initializeScheduler() {
		ScheduledExecutorService schedular = Executors.newSingleThreadScheduledExecutor();
		schedular.scheduleAtFixedRate(new Runnable() {
			public void run() {
				if (!cache.isEmpty()) {
					cacheAutoClear();
				}
			}
		}, SCHEDULAR_INTIAL_DELAY, SCHEDULAR_PERIOD, TimeUnit.MILLISECONDS);
	}

	/**
	 * Iterate over cache bucket and if current timestamp > (createdtimestamp + TTL)
	 * then that cache will be clear.
	 */
	private void cacheAutoClear() {
		for (Map.Entry<String, CacheEntries> entry : cache.entrySet()) {
			String key = entry.getKey();
			CacheEntries value = entry.getValue();
			long createdTimeStamp = value.getCreatedTimeStamp() + this.timeToLive;
			long currentTimeStamp = System.currentTimeMillis();
			if (createdTimeStamp < currentTimeStamp && key != null) {
				this.clear(key);
				this.index.remove(key);
			}
		}
	}

	/**
	 * Returns list of index consist cache keys
	 * 
	 * @return the list of index
	 */
	public List<String> getIndex() {
		return index;
	}

	/**
	 * Sets the new index
	 * 
	 * @param index index list
	 */
	public void setIndex(List<String> index) {
		this.index = index;
	}

	/**
	 * Returns cache bucket
	 * 
	 * @return cache bucket
	 */
	public ConcurrentMap<String, CacheEntries> getCache() {
		return cache;
	}

	/**
	 * Returns CacheEntries(value) for matching key
	 * 
	 * @param key Cache key
	 * @return CacheEntries(value)
	 */
	public CacheEntries getCache(String key) {
		return cache.get(key);
	}

	/**
	 * This method return size of cache
	 * 
	 * @return size of bucket
	 */
	public int getBucketCapacity() {
		return this.bucketCapacity;
	}

	/**
	 * This method sets bucket capacity
	 * 
	 * @param bucketCapacity size
	 */
	public void setBucketCapacity(int bucketCapacity) {
		this.bucketCapacity = bucketCapacity;
	}

	/**
	 * Shrink the cache bucket with given size
	 * 
	 * @implNote Method uses temporary storage to shrink the bucket, it nothing but
	 *           creates new bucket with new size and moves all data
	 * @param bucketCapacity new bucket capacity value
	 */
	public void shrinkBucket(int bucketCapacity) {
		ConcurrentMap<String, CacheEntries> temp = new ConcurrentHashMap<>(bucketCapacity);
		if (this.cache.isEmpty()) {
			this.cache = null;
			this.cache = temp;
			temp = null;
			((ArrayList<String>) this.index).trimToSize();
			this.bucketCapacity = bucketCapacity;
		} else {
			temp.putAll(this.cache);
			this.cache = null;
			this.cache = temp;
			temp = null;
			((ArrayList<String>) this.index).trimToSize();
			this.bucketCapacity = bucketCapacity;
		}

	}

	/**
	 * Provides bucket TTL
	 * 
	 * @return TTL value
	 */
	public long getTimeToLive() {
		return this.timeToLive;
	}

	/**
	 * Set bucket TTL
	 * 
	 * @param timeToLive TTL value
	 */
	public void setTimeToLive(long timeToLive) {
		if (this.timeToLive == -1) {
			this.timeToLive = timeToLive;
			initializeScheduler();
		} else {
			this.timeToLive = timeToLive;
		}

	}

	/**
	 * Stores the key and value in bucket object and update the indexing
	 * 
	 * @param key cache key
	 * @param value cache value
	 */
	public void setCache(String key, Object value) {
		this.cache.put(key, new CacheEntries(value));
		this.index.add(key);
	}

	/**
	 * Clears the cache from bucket for given key
	 * 
	 * @param key cache key
	 */
	public void clear(String key) {
		this.cache.remove(key);
		this.index.remove(key);
	}

	/**
	 * Return total number of keys/cache(entries) present in cache
	 * 
	 * @return size total number of entries
	 */
	public int getTotalEntries() {
		return this.cache.size();
	}

	/**
	 * Clears the bucket and index
	 */
	public void clear() {
		this.cache.clear();
		this.index.clear();
	}

	/**
	 * Remove oldest cache value from bucket
	 * @implNote get the 0th value from index and clears cache inside bucket accordingly
	 */
	public void removeOldestCache() {
		String oldestKey = this.index.get(0);
		if (oldestKey != null) {
			this.cache.remove(oldestKey);
			this.index.remove(oldestKey);
		}
	}

}
