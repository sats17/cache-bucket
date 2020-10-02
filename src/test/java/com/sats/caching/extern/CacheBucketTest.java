package com.sats.caching.extern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.sats.caching.internal.services.CacheController;

public class CacheBucketTest {
	
	CacheBucket cache = null;
	int size = 5;
	long bucketTimeLimit = 50000;
	
	@AfterEach
	public void cleanUp() {
		cache = null;
	}
	
	@Test
	public void testCacheController() {
		cache = new CacheController(size);
		int actualSize = cache.getBucketSize();
		assertEquals(size, actualSize);
	}
	
	@Test
	public void testSetCache() {
		cache = new CacheController(size);
		String value = "value1";
		cache.setCache("key1", value);
		assertEquals(value, cache.getCache("key1"));
	}
	
	@Test
	public void testCacheControllerWithTimeLimit() {
		cache = new CacheController(size, bucketTimeLimit);
		long actualTimeLimit = cache.getBucketTTL();
		assertEquals(bucketTimeLimit, actualTimeLimit);
	}
}
