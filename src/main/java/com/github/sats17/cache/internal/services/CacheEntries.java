package com.github.sats17.cache.internal.services;

/**
 * Object contains cache value and it's metadata
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
	 * Returns cache created time
	 * 
	 * @return cache created timestamp
	 */
	public long getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	/**
	 * Constructor use to stores cache values
	 * 
	 * @param cache value
	 */
	public CacheEntries(Object value) {
		this.value = value;
	}

	/**
	 * Returns cache value
	 * 
	 * @return cache value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * This method set cache value
	 * 
	 * @param cache value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
