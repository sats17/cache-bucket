package com.sats.caching.internal.services;

/**
 * CacheEntries pojo class, contains Cache value and it's metadata.
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
class CacheEntries {

	/**
	 * Timestamp sets in milliseconds when Cache Object creates
	 */
	private long createdTimeStamp = System.currentTimeMillis();

	/**
	 * Cache value
	 */
	private Object value;

	/**
	 * This method returns cache created time
	 * 
	 * @return createdTimeStamp
	 */
	public long getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	
	/**
	 * Constructor that stores cache value.
	 * 
	 * @param value
	 */
	public CacheEntries(Object value) {
		this.value = value;
	}

	/**
	 * This method return cache value
	 * 
	 * @return value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * This method set cache value.
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
