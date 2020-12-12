package com.github.sats17.caching.extern.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.github.sats17.cache.extern.CacheBucket;
import com.github.sats17.cache.internal.services.BucketController;

@RunWith(JUnitPlatform.class)
public class CacheBucketIntegrationTest {

	CacheBucket cache = null;
	int capacity = 5;
	long bucketTTL = 5000;

	public static final long CACHE_EXPIRY_TIME_LIMIT = 300;
	public static final long SCHEDULAR_INTIAL_DELAY = 0;
	public static final long SCHEDULAR_PERIOD = 1;
	public static final int BUCKET_CAPACITY = 200;
	public static final long BUCKET_TTL = 14400000;
	public static final int KEY_LENGTH = 30;

	public static final String BUCKET_CAPACITY_NOT_VALID_MESSAGE = "Bucket capacity should be between 1 to "
			+ BUCKET_CAPACITY;
	public static final String BUCKET_TTL_NOT_VALID_MESSAGE = "Time to live should be between 1 to " + BUCKET_TTL;
	public static final String CACHE_KEY_NOT_VALID_MESSAGE = "Key should be proper, max length of key is " + KEY_LENGTH;
	public static final String CACHE_VALUE_NOT_VALID_MESSAGE = "Value should not be null";

	@AfterEach
	public void cleanUp() {
		cache = null;
	}

	/*
	 * All tests for Cache with capacity only.
	 */
	@Test
	public void testCacheController() {
		cache = new BucketController(capacity);
		int actualCapacity = cache.getBucketCapacity();
		assertEquals(capacity, actualCapacity);
	}

	@Test
	public void testSetCache() {
		cache = new BucketController(capacity);
		String value = "value1";
		cache.setCache("key1", value);
		assertEquals(value, cache.getCache("key1"));
	}

	@Test
	public void testGetCache() {
		cache = new BucketController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertTrue("value3".equals(cache.getCache("third")));
	}

	@Test
	public void testGetCacheWhenWrongKeyPassedAndMethodReturnsNull() {
		cache = new BucketController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(null, cache.getCache("fourth"));
	}

	@Test
	public void testGetAll() {
		cache = new BucketController(capacity);
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
		cache = new BucketController(capacity);
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
		cache = new BucketController(capacity);
		assertEquals(capacity, cache.getBucketCapacity());
	}

	@Test
	public void setBucketCapacity() {
		cache = new BucketController(capacity);
		cache.setBucketCapacity(3);
		assertEquals(3, cache.getBucketCapacity());
	}

	@Test
	public void testSetBucketCapacityLessThanEarlierCapacityWithNoDataInBucket() {
		cache = new BucketController(capacity);
		cache.setBucketCapacity(2);
		assertEquals(2, cache.getBucketCapacity());
	}

	@Test
	public void testSetBucketCapacityLessThanEarlierCapacity() {
		cache = new BucketController(capacity);
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
	public void testSetBucketCapacityGreaterThanEarlierCapacity() {
		cache = new BucketController(capacity);
		cache.setBucketCapacity(7);
		assertEquals(7, cache.getBucketCapacity());
	}

	@Test
	public void testClearCacheByKey() {
		cache = new BucketController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.clear("second");
		assertNull(cache.getCache("second"));
	}

	@Test
	public void testClearAllCache() {
		cache = new BucketController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.clear();
		assertEquals(0, cache.getTotalEntries());
	}

	@Test
	public void testGetTotalEntries() {
		cache = new BucketController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(3, cache.getTotalEntries());
	}

	@Test
	public void testSetBucketWithCapacityLessThanOne() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new BucketController(-1),
				"Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_CAPACITY_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetBucketWithCapacityGreaterThanTwoHundread() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new BucketController(201),
				"Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_CAPACITY_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetCacheWithInvalidKey() {
		cache = new BucketController(14);
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cache.setCache(" ", "avc"),
				"Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_KEY_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetCacheWithInvalidKeyAsNull() {
		cache = new BucketController(14);
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> cache.setCache(null, "avc"), "Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_KEY_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetCacheWithValueAsNull() {
		cache = new BucketController(14);
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> cache.setCache("Test", null), "Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_VALUE_NOT_VALID_MESSAGE);
	}

	@Test
	public void testGetTTL() {
		cache = new BucketController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(-1, cache.getBucketTTL());
	}

	@Test
	public void testSetTTL() {
		cache = new BucketController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setBucketTTL(1000);
		assertEquals(1000, cache.getBucketTTL());
	}

	@Test
	public void testSetTTLWithAutoCacheClearAfterBucketMigration() throws InterruptedException {
		cache = new BucketController(capacity);
		cache.setCache("first", "value1");
		Thread.sleep(1000);
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setBucketTTL(500);
		Thread.sleep(10);
		assertEquals(2, cache.getTotalEntries());
		Thread.sleep(1000);
		assertEquals(0, cache.getTotalEntries());
		assertEquals(500, cache.getBucketTTL());
	}

	@Test
	public void testIsEmptyMethodTrueCondition() throws InterruptedException {
		cache = new BucketController(capacity);
		assertTrue(cache.isEmpty());
	}

	@Test
	public void testIsEmptyMethodFalseCondition() throws InterruptedException {
		cache = new BucketController(capacity);
		cache.setCache("first", "value1");
		assertFalse(cache.isEmpty());
	}

	/*
	 * All Test cache for cache bucket with capacity and TTL.
	 */
	@Test
	public void testCacheControllerWithCapacityAndTTL() {
		cache = new BucketController(capacity, bucketTTL);
		long actualTTL = cache.getBucketTTL();
		assertEquals(bucketTTL, actualTTL);
	}

	@Test
	public void testSetCacheWithBucketTTL() {
		cache = new BucketController(capacity, bucketTTL);
		String value = "value1";
		cache.setCache("key1", value);
		assertEquals(value, cache.getCache("key1"));
	}

	@Test
	public void testGetCacheWithBucketTTL() {
		cache = new BucketController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertTrue("value3".equals(cache.getCache("third")));
	}

	@Test
	public void testGetCacheWithBucketTTLWhenWrongKeyPassedAndMethodReturnsNull() {
		cache = new BucketController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(null, cache.getCache("fourth"));
	}

	@Test
	public void testGetAllWithBucketTTL() {
		cache = new BucketController(capacity, bucketTTL);
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
	public void testOldestCacheRemovalWithBucketTTL() {
		cache = new BucketController(capacity, bucketTTL);
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
	public void testGetBucketCapacityWithBucketTTL() {
		cache = new BucketController(capacity, bucketTTL);
		assertEquals(capacity, cache.getBucketCapacity());
	}

	@Test
	public void setBucketCapacityWithBucketTTL() {
		cache = new BucketController(capacity, bucketTTL);
		cache.setBucketCapacity(3);
		assertEquals(3, cache.getBucketCapacity());
	}

	@Test
	public void testSetBucketCapacityLessThanEarlierCapacityWithBucketTTL() {
		cache = new BucketController(capacity, bucketTTL);
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
	public void testSetBucketCapacityGreaterThanEarlierCapacityWithTTL() {
		cache = new BucketController(capacity, bucketTTL);
		cache.setBucketCapacity(7);
		assertEquals(7, cache.getBucketCapacity());
	}

	@Test
	public void testClearCacheByKeyWithTTL() {
		cache = new BucketController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.clear("second");
		assertNull(cache.getCache("second"));
	}

	@Test
	public void testClearAllCacheWithTTL() {
		cache = new BucketController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.clear();
		assertEquals(0, cache.getTotalEntries());
	}

	@Test
	public void testGetTotalEntriesWithTTL() {
		cache = new BucketController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(3, cache.getTotalEntries());
	}

	@Test
	public void testAutoCacheClear() throws InterruptedException {
		cache = new BucketController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		Thread.sleep(5500);
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setCache("four", "value4");
		cache.setCache("five", "value5");
		assertEquals(4, cache.getTotalEntries());
	}

	@Test
	public void testSetBucketTTLLessThanPreviousTTL() throws InterruptedException {
		cache = new BucketController(capacity, 10000);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setBucketTTL(1000);
		Thread.sleep(1500);
		assertEquals(0, cache.getTotalEntries());
	}

	@Test
	public void testSetBucketTTLMoreThanPreviousTTL() throws InterruptedException {
		cache = new BucketController(capacity, 1000);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setBucketTTL(10000);
		Thread.sleep(2000);
		assertEquals(3, cache.getTotalEntries());
	}

	@Test
	public void testSetBucketWithCapacityLessThanOneWithBucketTTLMethod() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> new BucketController(-1, 1000), "Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_CAPACITY_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetBucketWithCapacityGreaterThanTwoHundreadWithBucketTTLMethod() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> new BucketController(201, 15000), "Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_CAPACITY_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetCacheWithInvalidKeyWithBucketTTLMethod() {
		cache = new BucketController(14, 2000);
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cache.setCache(" ", "avc"),
				"Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_KEY_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetCacheWithInvalidKeyAsNullWithBucketTTLMethod() {
		cache = new BucketController(14, 2000);
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> cache.setCache(null, "avc"), "Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_KEY_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetCacheWithValueAsNullWithBucketTTLMethod() {
		cache = new BucketController(14, 20000);
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> cache.setCache("Test", null), "Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_VALUE_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetBucketWithTTLGreaterThan14400000WithBucketTTLMethod() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> new BucketController(14, 144000001), "Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_TTL_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetBucketWithTTLLessThanZeroWithBucketTTLMethod() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> new BucketController(14, -123), "Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_TTL_NOT_VALID_MESSAGE);
	}

	@Test
	public void testSetBucketWhileSettingTTLWithWrongValue() {
		cache = new BucketController(14, 20000);
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> cache.setBucketTTL(-100),
				"Expected doThing() to throw, but it didn't");
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_TTL_NOT_VALID_MESSAGE);
	}

	@Test
	public void testIsEmptyMethodTrueConditionWithBucketTTLMethod() throws InterruptedException {
		cache = new BucketController(capacity, bucketTTL);
		assertTrue(cache.isEmpty());
	}

	@Test
	public void testIsEmptyMethodFalseConditionWithBucketTTLMethod() throws InterruptedException {
		cache = new BucketController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		assertFalse(cache.isEmpty());
	}
}
