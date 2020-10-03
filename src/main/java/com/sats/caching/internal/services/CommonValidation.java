package com.sats.caching.internal.services;

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

	static void validateCapacity(int capacity) {
		if (capacity <= 0 || capacity > 200) {
			throw new IllegalArgumentException("Bucket capacity should be between 1 to 200");
		}
	}

	static void validateTTL(long timeToLive) {
		if (timeToLive <= 0 || timeToLive > 14400000) {
			throw new IllegalArgumentException("Time to live should be between 1 to 14400000");
		}
	}

	static void validateKey(String key) {
		if (key == null || key.isBlank() || key.length() > 10) {
			throw new IllegalArgumentException("Key should be proper, max length of key is 10.");
		}
	}

	static void validateValue(Object value) {
		if (value == null) {
			throw new IllegalArgumentException("Value should not be null");
		}
	}

}
