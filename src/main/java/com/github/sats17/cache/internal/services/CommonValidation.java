package com.github.sats17.cache.internal.services;

import static com.github.sats17.cache.internal.services.Constants.*;
/**
 * Validation class contains all common validation methods
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
class CommonValidation {

	/**
	 * Validation constructor
	 */
	private CommonValidation() {
	}

	/**
	 * Validates if capacity is appropriate or not
	 * 
	 * @param capacity capacity
	 */
	static void validateBucketCapacity(int capacity) {
		if (capacity <= 0 || capacity > BUCKET_CAPACITY) {
			throw new IllegalArgumentException(BUCKET_CAPACITY_NOT_VALID_MESSAGE);
		}
	}

	/**
	 * Validates if ttl is appropriate or not
	 * 
	 * @param timeToLive TTL value
	 */
	static void validateBucketTTL(long timeToLive) {
		if (timeToLive <= 0 || timeToLive > BUCKET_TTL) {
			throw new IllegalArgumentException(BUCKET_TTL_NOT_VALID_MESSAGE);
		}
	}

	/**
	 * Validates if key is appropriate or not
	 * 
	 * @param key key
	 */
	static void validateKey(String key) {
		if (key == null || key.trim().length() == 0 || key.length() > KEY_LENGTH) {
			throw new IllegalArgumentException(CACHE_KEY_NOT_VALID_MESSAGE);
		}
	}

	/**
	 * Validates if cache value is appropriate or not
	 * 
	 * @param value cache value
	 */
	static void validateValue(Object value) {
		if (value == null) {
			throw new IllegalArgumentException(CACHE_VALUE_NOT_VALID_MESSAGE);
		}
	}

}
