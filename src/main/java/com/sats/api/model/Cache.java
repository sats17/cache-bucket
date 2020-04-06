package com.sats.api.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<K, V> {
	
	private long timeLimit;

	private LinkedHashMap<K, Storage> cache = new LinkedHashMap<K, Storage>();

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
