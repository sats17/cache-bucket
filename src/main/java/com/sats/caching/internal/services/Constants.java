package com.sats.caching.internal.services;

/**
 * Contains constants of library.
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
class Constants {
	
	private Constants() {}

	public static final long CACHE_EXPIRY_TIME_LIMIT = 300; 
	public static final long SCHEDULAR_INTIAL_DELAY = 0;
	public static final long SCHEDULAR_PERIOD = 1;
	public static final int BUCKET_CAPACITY = 200;
	public static final long BUCKET_TTL = 14400000;
	public static final int KEY_LENGTH = 30;
	
	public static final String BUCKET_CAPACITY_NOT_VALID_MESSAGE = "Bucket capacity should be between 1 to "+BUCKET_CAPACITY;
	public static final String BUCKET_TTL_NOT_VALID_MESSAGE = "Time to live should be between 1 to "+BUCKET_TTL;
	public static final String CACHE_KEY_NOT_VALID_MESSAGE = "Key should be proper, max length of key is "+KEY_LENGTH;
	public static final String CACHE_VALUE_NOT_VALID_MESSAGE = "Value should not be null";
}
