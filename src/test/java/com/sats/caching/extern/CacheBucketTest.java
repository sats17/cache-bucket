package com.sats.caching.extern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.sats.caching.internal.services.CacheController;

public class CacheBucketTest {
	
	CacheBucket cache = null;
	int capacity = 5;
	long bucketTTL = 50000;
	
	@AfterEach
	public void cleanUp() {
		cache = null;
	}
	
	@Test
	public void testCacheController() {
		cache = new CacheController(capacity);
		int actualCapacity = cache.getBucketCapacity();
		assertEquals(capacity, actualCapacity);
	}
	
	@Test
	public void testSetCache() {
		cache = new CacheController(capacity);
		String value = "value1";
		cache.setCache("key1", value);
		assertEquals(value, cache.getCache("key1"));
	}
	
	@Test
	public void testGetCache() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertTrue("value3".equals(cache.getCache("third")));
	}
	
	@Test
	public void testGetCacheForNullResult() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(null, cache.getCache("fourth"));
	}
	
	@Test
	public void testGetAll() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		Map<String, Object> result = cache.getAll();
		assertEquals(3, result.size());
		assertEquals("value1", result.get("first"));
		assertEquals("value2", result.get("second"));
		assertEquals("value3", result.get("third"));
	}
	
	@Test
	public void testOldestCacheRemoval() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setCache("four", "value4");
		cache.setCache("five", "value5");
		cache.setCache("six", "value6");
		assertEquals(5, cache.getTotalEntries());
		assertNull(cache.getCache("first"));
	}
	
	@Test
	public void testGetBucketCapacity() {
		cache = new CacheController(capacity);
		assertEquals(capacity, cache.getBucketCapacity());
	}
	
	@Test
	public void setBucketCapacity() {
		cache = new CacheController(capacity);
		cache.setBucketCapacity(3);
		assertEquals(3, cache.getBucketCapacity());
	}
	
	@Test
	public void setBucketCapacityLessThanEarlierCapacity() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setCache("four", "value4");
		cache.setCache("five", "value5");
		cache.setBucketCapacity(2);
		assertEquals(2, cache.getTotalEntries());
		assertEquals(2, cache.getBucketCapacity());
	}
	
	@Test
	public void setBucketCapacityGreaterThanEarlierCapacity() {
		cache = new CacheController(capacity);
		cache.setBucketCapacity(7);
		assertEquals(7, cache.getBucketCapacity());
	}
	
	@Test
	public void testCacheControllerWithTTL() {
		cache = new CacheController(capacity, bucketTTL);
		long actualTTL = cache.getBucketTTL();
		assertEquals(bucketTTL, actualTTL);
	}
}
