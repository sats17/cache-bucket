package com.github.sats17.cache.internal.services;

import static com.github.sats17.cache.internal.services.Constants.*;
/**
 * Validation class contains all common validation methods.
 * 
 * @version 1.0.0
 * @author Sats17
 *
 */
class CommonValidation {

	private CommonValidation() {
	}

	static void validateBucketCapacity(int capacity) {
		if (capacity <= 0 || capacity > BUCKET_CAPACITY) {
			throw new IllegalArgumentException(BUCKET_CAPACITY_NOT_VALID_MESSAGE);
		}
	}

	static void validateBucketTTL(long timeToLive) {
		if (timeToLive <= 0 || timeToLive > BUCKET_TTL) {
			throw new IllegalArgumentException(BUCKET_TTL_NOT_VALID_MESSAGE);
		}
	}

	static void validateKey(String key) {
		if (key == null || key.trim().length() == 0 || key.length() > KEY_LENGTH) {
			throw new IllegalArgumentException(CACHE_KEY_NOT_VALID_MESSAGE);
		}
	}

	static void validateValue(Object value) {
		if (value == null) {
			throw new IllegalArgumentException(CACHE_VALUE_NOT_VALID_MESSAGE);
		}
	}

}
