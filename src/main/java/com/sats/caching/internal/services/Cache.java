package com.sats.caching.internal.services;

import static com.sats.caching.internal.services.Constants.SCHEDULAR_INTIAL_DELAY;
import static com.sats.caching.internal.services.Constants.SCHEDULAR_PERIOD;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @version 1.0.0
 * @author sats17
 *
 * @param <K>
 * @param <V>
 */
class Cache<K, V> {

	/**
	 * Cache time limit variable.
	 */
	private long timeLimit;

	/**
	 * Cache size variable.
	 */
	private int size;

	/**
	 * Cache map.
	 */
	private ConcurrentHashMap<K, Storage> cache;

	/**
	 * Default constructor for cache.
	 */
	public Cache() {
		cache = new ConcurrentHashMap<K, Storage>(); 
	}

	/**
	 * Parameterized constructor for cache. It will initialized scheduler once called.
	 * @param timeLimit
	 */
	public Cache(long timeLimit) {
		cache = new ConcurrentHashMap<K, Storage>(); 
		this.timeLimit = timeLimit;
		initializeScheduler(timeLimit);
	}

	/**
	 * This method initialized the scheduler and checks if cache is empty or not every seconds.
	 * if cache is not empty then it will call cacheAutoClear method.
	 * @param timeLimit
	 * @return void
	 */
	private void initializeScheduler(final long timeLimit) {
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
	 * This method iterate over cache map and compare every storage objects with it's created time stamp and current
	 * time stamp. If created time stamp  is less than current time stamp then it will clear that cache by it's key.
	 * @return void
	 */
	private void cacheAutoClear() {
		for (Map.Entry<K, Storage> entry : cache.entrySet()) {
			K key = entry.getKey();
			Storage value = entry.getValue();
			long createdTimeStamp = value.getCreatedTimeStamp() + timeLimit;
			long currentTimeStamp = System.currentTimeMillis();
			if (createdTimeStamp < currentTimeStamp) {
				clear(key);
			}
		}
	}

	/**
	 * This method return all cache.
	 * @return ConcurrentHashMap<K, Storage>
	 */
	public HashMap<K, Object> getCache() {
		HashMap<K, Object> returningObject = new HashMap<K, Object>();
		for (Map.Entry<K, Storage> entry : this.cache.entrySet()) {
		    K key = (K) entry.getKey().toString();
		    Storage value = entry.getValue();
		    returningObject.put(key, value);
		}
		return returningObject;
	}

	/**
	 * This method return size of cache.
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * This method sets cache size.
	 * @param size
	 * @return void
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * This method get cache time limit.
	 * @return timeLimit
	 */
	public long getTimeLimit() {
		return timeLimit;
	}

	/**
	 * This method set cache time limit.
	 * @param timeLimit
	 * @return void
	 */
	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * This method stores key and value in concurrentHashMap.
	 * @param key
	 * @param value
	 * @return void
	 */
	public void setCache(K key, V value) {
		this.cache.put(key, new Storage(value));
	}

	/**
	 * This remove cache by key from concurrentHashMap.
	 * @param key
	 * @return void
	 */
	public void clear(K key) {
		this.cache.remove(key);
	}

	/**
	 * This method return current size of cache.
	 * @return size
	 */
	public int getCacheSize() {
		return this.cache.size();
	}

	/**
	 * This method clear cache.
	 */
	public void clear() {
		this.cache.clear();
	}

	/**
	 * This method remove oldest element from cache.
	 * @return void
	 */
	public void removeOldestCache() {
		long greatestTimestamp = System.currentTimeMillis();
		K removalKey = null;
		for (Map.Entry<K, Storage> entry : cache.entrySet()) {
			if (entry.getValue().getCreatedTimeStamp() <= greatestTimestamp) {
				greatestTimestamp = entry.getValue().getCreatedTimeStamp();
			}
			removalKey = entry.getKey();
		}
		this.cache.remove(removalKey);
	}

	@Override
	public String toString() {
		return "Cache [timeLimit=" + timeLimit + ", cache=" + cache + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cache == null) ? 0 : cache.hashCode());
		result = prime * result + (int) (timeLimit ^ (timeLimit >>> 32));
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
		Cache<K, V> other = (Cache) obj;
		if (cache == null) {
			if (other.cache != null)
				return false;
		} else if (!cache.equals(other.cache))
			return false;
		if (timeLimit != other.timeLimit)
			return false;
		return true;
	}

}
