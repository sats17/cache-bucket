package com.sats.caching.internal.services;

import static com.sats.caching.internal.services.Constants.*;
/**
 * Validation class contains all common validation methods.
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
public class CommonValidation {

	private CommonValidation() {
	}

	static void validateBucketCapacity(int capacity) {
		if (capacity <= 0 || capacity > BUCKET_CAPACITY) {
			throw new IllegalArgumentException("Bucket capacity should be between 1 to "+BUCKET_CAPACITY);
		}
	}

	static void validateBucketTTL(long timeToLive) {
		if (timeToLive <= 0 || timeToLive > BUCKET_TTL) {
			throw new IllegalArgumentException("Time to live should be between 1 to "+BUCKET_TTL);
		}
	}

	static void validateKey(String key) {
		if (key == null || key.isBlank() || key.length() > KEY_LENGTH) {
			throw new IllegalArgumentException("Key should be proper, max length of key is "+KEY_LENGTH);
		}
	}

	static void validateValue(Object value) {
		if (value == null) {
			throw new IllegalArgumentException("Value should not be null");
		}
	}

}
