package com.sats.caching.internal.services;

import static com.sats.caching.internal.services.Constants.SCHEDULAR_INTIAL_DELAY;
import static com.sats.caching.internal.services.Constants.SCHEDULAR_PERIOD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Bucket class contains all keys and cache.
 * 
 * @version 1.0.0
 * @author Sats17
 *
 * @param <K>
 * @param <V>
 */
class Bucket<K, V> {

	/**
	 * Bucket TTL variable.
	 */
	private long timeToLive;

	/**
	 * Bucket capacity variable.
	 */
	private int bucketCapacity;
	
	/**
	 * Bucket index storage, this consist all keys.
	 */
	private List<String> index;

	/**
	 * Map contains key and cacheEntry.
	 */
	private ConcurrentMap<K, CacheEntries> cache;
	
	@SuppressWarnings("unused")
	private Bucket() {}

	/**
	 * Constructor for bucket.
	 */
	public Bucket(int bucketCapacity) {
		this.setIndex(new ArrayList<>(bucketCapacity));
		this.cache = new ConcurrentHashMap<>(bucketCapacity);
		this.bucketCapacity = bucketCapacity;
	}

	/**
	 * Parameterized constructor for cache. It will initialized scheduler for auto
	 * cache clearing.
	 * 
	 * @param timeToLive : TTL value.
	 */
	public Bucket(int bucketCapacity, long timeToLive) {
		this.setIndex(new ArrayList<>(bucketCapacity));
		this.cache = new ConcurrentHashMap<>(bucketCapacity);
		this.bucketCapacity = bucketCapacity;
		this.timeToLive = timeToLive;
		initializeScheduler();
	}

	/**
	 * This method initialized the scheduler and checks if cache is empty or not for
	 * every seconds. if cache is not empty then it will call cacheAutoClear method.
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
	 * This method iterate over cache map and if current timestamp is greater than cache createdtimestamp
	 * plus TTL then that cache will be clear.
	 */
	private void cacheAutoClear() {
		for (Map.Entry<K, CacheEntries> entry : cache.entrySet()) {
			K key = entry.getKey();
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
	 * @return the index
	 */
	public List<String> getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(List<String> index) {
		this.index = index;
	}
	
	/**
	 * This method returns cache bucket.
	 * 
	 * @return ConcurrentHashMap<K, CacheEntries>
	 */
	public ConcurrentMap<K, CacheEntries> getCache() {
		return cache;
	}

	/**
	 * This method returns CacheEntries object for matching key.
	 * 
	 * @param key : Unique which stores in bucket for particular cache.
	 * @return CacheEntries
	 */
	public CacheEntries getCache(String key) {
		return cache.get(key);
	}

	/**
	 * This method return size of cache.
	 * 
	 * @return size
	 */
	public int getBucketCapacity() {
		return this.bucketCapacity;
	}

	/**
	 * This method sets bucket capacity.
	 * 
	 * @param size
	 * @return void
	 */
	public void setBucketCapacity(int bucketCapacity) {
		this.bucketCapacity = bucketCapacity;
	}
	
	/**
	 * Method shrink the cache bucket for given size.
	 * 
	 * @param bucketCapacity : bucket capacity.
	 */
	public void shrinkBucket(int bucketCapacity) {
		ConcurrentMap<K, CacheEntries> temp = new ConcurrentHashMap<>(bucketCapacity);
		if(this.cache.isEmpty()) {
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
	 * This method get bucket TTL.
	 * 
	 * @return timeToLive
	 */
	public long getTimeToLive() {
		return this.timeToLive;
	}

	/**
	 * This method set bucket TTL.
	 * 
	 * @param timeToLive
	 * @return void
	 */
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * This method stores key and value in concurrentHashMap.
	 * 
	 * @param key
	 * @param value
	 * @return void
	 */
	public void setCache(K key, V value) {
		this.cache.put(key, new CacheEntries(value));
		this.index.add((String) key);
	}

	/**
	 * This remove cache by key from concurrentHashMap.
	 * 
	 * @param key
	 * @return void
	 */
	public void clear(K key) {
		this.cache.remove(key);
		this.index.remove(key);
	}

	/**
	 * This method return total number of keys present in cache.
	 * 
	 * @return size
	 */
	public int getTotalEntries() {
		return this.cache.size();
	}

	/**
	 * This method clear cache.
	 */
	public void clear() {
		this.cache.clear();
		this.index.clear();
	}

	/**
	 * This method remove oldest value from bucket, using index.
	 */
	public void removeOldestCache() {
		String oldestKey = this.index.get(0);
		if(oldestKey != null) {
			this.cache.remove(oldestKey);
			this.index.remove(oldestKey);
		}
	}

	@Override
	public String toString() {
		return "Cache [timeToLive=" + timeToLive + ", cache=" + cache + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cache == null) ? 0 : cache.hashCode());
		result = prime * result + (int) (timeToLive ^ (timeToLive >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Bucket<K, V> other = (Bucket<K, V>) obj;
		if (cache == null) {
			if (other.cache != null)
				return false;
		} else if (!cache.equals(other.cache))
			return false;
		if (timeToLive != other.timeToLive)
			return false;
		return true;
	}

}
