package com.sats.internal.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache<K, V> {

	private long timeLimit;

	private ConcurrentHashMap<K, Storage> cache = new ConcurrentHashMap<K, Storage>();

	public Cache() {}
	
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
						cacheClearByThread();
					}
				}
			}
		});
		t.start();
	}

	private void cacheClearByThread() {
			for (Map.Entry<K, Storage> entry : cache.entrySet()) {
				K key = entry.getKey();
				Storage value = entry.getValue();
				long createdTimeStamp = value.getCreatedTimeStamp() + timeLimit;
				long currentTimeStamp = System.currentTimeMillis();
				if (createdTimeStamp < currentTimeStamp) {
					removeCache(key);
				}
			}
	}

	public ConcurrentHashMap<K, Storage> getCache() {
			return this.cache;
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

	public void removeCache(K key) {
		this.cache.remove(key);
	}

	public int getCacheSize() {
			return this.cache.size();
	}

	public void cleanCache() {
			this.cache.clear();
	}

	public void removeOldestElement() {
		long greatestTimestamp = System.currentTimeMillis();
		K removalKey = null;
		for (Map.Entry<K, Storage> entry : cache.entrySet()) {
			if(entry.getValue().getCreatedTimeStamp() <= greatestTimestamp) {
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

}
