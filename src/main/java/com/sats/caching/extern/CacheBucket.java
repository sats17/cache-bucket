package com.sats.caching.extern;

import java.util.HashMap;


public interface CacheBucket {

	/**
	 * This method set cache.
	 * @param key : Unique cache key { Type: String}.
	 * @param value : Cache value that you want store into bucket.
	 * @return void
	 */
	public void setCache(String key,Object value);
	
	/**
	 * Not implemented
	 * @param key
	 * @param value
	 * @param canCacheExpire
	 */
	public void setCache(String key,Object value,Boolean canCacheExpire);
	
	/**
	 * Return Cache value
	 * @param key : Unique key that present in cache bucket.
	 * @return Object
	 */
	public Object getCache(String key);
	
	public HashMap<Object, Object> getAll();
	
	public int getBucketSize();
	
	public void clear(String key);
	
	public void clear();
	
	public long getBucketTimeLimit();
	
	public void setBucketTimeLimit();
	
	public int getTotalEntries();
	
}
