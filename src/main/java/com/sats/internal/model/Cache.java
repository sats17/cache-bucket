package com.sats.internal.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<K, V> {

	private long timeLimit;
	
	private int size;

	private ConcurrentHashMap<K, Storage> cache = new ConcurrentHashMap<K, Storage>();

	public Cache() {
	}

	public Cache(long timeLimit) {
		this.timeLimit = timeLimit;
		initializeThread(timeLimit);

	}

	private void initializeThread(final long timeLimit) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (cache.isEmpty()) {
						continue;
					} else {
						cacheAutoClear();
					}
				}
			}
		});
		t.start();
	}

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

	public ConcurrentHashMap<K, Storage> getCache() {
		return this.cache;
	}
	
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}

	public void setCache(K key, V value) {
		this.cache.put(key, new Storage(value));
	}

	public void clear(K key) {
		this.cache.remove(key);
	}

	public int getCacheSize() {
		return this.cache.size();
	}

	public void clear() {
		this.cache.clear();
	}

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
		Cache other = (Cache) obj;
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
