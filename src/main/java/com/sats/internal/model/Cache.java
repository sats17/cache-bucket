package com.sats.internal.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<K, V> {
	
	private long timeLimit;
	
	private long createdTimeStamp = System.currentTimeMillis();

	private LinkedHashMap<K, Storage> cache = new LinkedHashMap<K, Storage>();

	public Cache(final long timeLimit) {
		this.timeLimit = timeLimit;
		Thread t = new Thread(new Runnable() {
			public void run() {
				long totalTime = createdTimeStamp + timeLimit;
				System.out.println("Before total time "+totalTime);
				while(totalTime > System.currentTimeMillis()) {
					System.out.println(getCache());	
				}
			}
		});
		t.start();
	}
	
	public LinkedHashMap<K, Storage> getCache() {
		return cache;
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
	
	public int getCacheSize() {
		return this.cache.size();
	}
	
	public void cleanCache() {
		this.cache.clear();
	}
	
	public void removeOldestElement() {
		Map.Entry<K,Storage> entry = this.cache.entrySet().iterator().next();
		K key= entry.getKey();
		this.cache.remove(key);
	}


	@Override
	public String toString() {
		return "Cache [timeLimit=" + timeLimit + ", cache=" + cache + "]";
	}
	
	
}
