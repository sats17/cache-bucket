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
		if (capacity <= 0 || capacity > bucketCapacity) {
			throw new IllegalArgumentException("Bucket capacity should be between 1 to "+bucketCapacity);
		}
	}

	static void validateBucketTTL(long timeToLive) {
		if (timeToLive <= 0 || timeToLive > bucketTTL) {
			throw new IllegalArgumentException("Time to live should be between 1 to "+bucketTTL);
		}
	}

	static void validateKey(String key) {
		if (key == null || key.isBlank() || key.length() > keyLength) {
			throw new IllegalArgumentException("Key should be proper, max length of key is "+keyLength);
		}
	}

	static void validateValue(Object value) {
		if (value == null) {
			throw new IllegalArgumentException("Value should not be null");
		}
	}

}
