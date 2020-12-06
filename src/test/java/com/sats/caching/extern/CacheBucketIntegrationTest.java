package com.sats.caching.extern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.sats.caching.internal.services.CacheController;

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
	
	public static final String BUCKET_CAPACITY_NOT_VALID_MESSAGE = "Bucket capacity should be between 1 to "+BUCKET_CAPACITY;
	public static final String BUCKET_TTL_NOT_VALID_MESSAGE = "Time to live should be between 1 to "+BUCKET_TTL;
	public static final String CACHE_KEY_NOT_VALID_MESSAGE = "Key should be proper, max length of key is "+KEY_LENGTH;
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
	public void testGetCacheWhenWrongKeyPassedAndMethodReturnsNull() {
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
	public void testSetBucketCapacityLessThanEarlierCapacityWithNoDataInBucket() {
		cache = new CacheController(capacity);
		cache.setBucketCapacity(2);
		assertEquals(2, cache.getBucketCapacity());
	}

	@Test
	public void testSetBucketCapacityLessThanEarlierCapacity() {
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
	public void testSetBucketCapacityGreaterThanEarlierCapacity() {
		cache = new CacheController(capacity);
		cache.setBucketCapacity(7);
		assertEquals(7, cache.getBucketCapacity());
	}

	@Test
	public void testClearCacheByKey() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.clear("second");
		assertNull(cache.getCache("second"));
	}

	@Test
	public void testClearAllCache() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.clear();
		assertEquals(0, cache.getTotalEntries());
	}

	@Test
	public void testGetTotalEntries() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(3, cache.getTotalEntries());
	}
	
	@Test
	public void testSetBucketWithCapacityLessThanOne() {
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> new CacheController(-1),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_CAPACITY_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testSetBucketWithCapacityGreaterThanTwoHundread() {
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> new CacheController(201),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_CAPACITY_NOT_VALID_MESSAGE);
	}
	
	
	
	@Test
	public void testSetCacheWithInvalidKey() {
		cache = new CacheController(14);
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> cache.setCache(" ", "avc"),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_KEY_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testSetCacheWithInvalidKeyAsNull() {
		cache = new CacheController(14);
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> cache.setCache(null, "avc"),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_KEY_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testSetCacheWithValueAsNull() {
		cache = new CacheController(14);
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> cache.setCache("Test", null),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_VALUE_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testGetTTL() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(-1, cache.getBucketTTL());
	}
	
	@Test
	public void testSetTTL() {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setBucketTTL(1000);
		assertEquals(1000, cache.getBucketTTL());
	}
	
	@Test
	public void testSetTTLWithAutoCacheClearAfterBucketMigration() throws InterruptedException {
		cache = new CacheController(capacity);
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
		cache = new CacheController(capacity);
		assertTrue(cache.isEmpty());
	}
	
	@Test
	public void testIsEmptyMethodFalseCondition() throws InterruptedException {
		cache = new CacheController(capacity);
		cache.setCache("first", "value1");
		assertFalse(cache.isEmpty());
	}


	/*
	 * All Test cache for cache bucket with capacity and TTL.
	 */
	@Test
	public void testCacheControllerWithCapacityAndTTL() {
		cache = new CacheController(capacity, bucketTTL);
		long actualTTL = cache.getBucketTTL();
		assertEquals(bucketTTL, actualTTL);
	}

	@Test
	public void testSetCacheWithBucketTTL() {
		cache = new CacheController(capacity, bucketTTL);
		String value = "value1";
		cache.setCache("key1", value);
		assertEquals(value, cache.getCache("key1"));
	}
	
	@Test
	public void testGetCacheWithBucketTTL() {
		cache = new CacheController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertTrue("value3".equals(cache.getCache("third")));
	}
	
	@Test
	public void testGetCacheWithBucketTTLWhenWrongKeyPassedAndMethodReturnsNull() {
		cache = new CacheController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(null, cache.getCache("fourth"));
	}
	
	@Test
	public void testGetAllWithBucketTTL() {
		cache = new CacheController(capacity, bucketTTL);
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
		cache = new CacheController(capacity, bucketTTL);
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
		cache = new CacheController(capacity, bucketTTL);
		assertEquals(capacity, cache.getBucketCapacity());
	}
	
	@Test
	public void setBucketCapacityWithBucketTTL() {
		cache = new CacheController(capacity, bucketTTL);
		cache.setBucketCapacity(3);
		assertEquals(3, cache.getBucketCapacity());
	}

	@Test
	public void testSetBucketCapacityLessThanEarlierCapacityWithBucketTTL() {
		cache = new CacheController(capacity, bucketTTL);
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
		cache = new CacheController(capacity, bucketTTL);
		cache.setBucketCapacity(7);
		assertEquals(7, cache.getBucketCapacity());
	}

	@Test
	public void testClearCacheByKeyWithTTL() {
		cache = new CacheController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.clear("second");
		assertNull(cache.getCache("second"));
	}

	@Test
	public void testClearAllCacheWithTTL() {
		cache = new CacheController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.clear();
		assertEquals(0, cache.getTotalEntries());
	}

	@Test
	public void testGetTotalEntriesWithTTL() {
		cache = new CacheController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		assertEquals(3, cache.getTotalEntries());
	}


	@Test
	public void testAutoCacheClear() throws InterruptedException {
		cache = new CacheController(capacity, bucketTTL);
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
		cache = new CacheController(capacity, 10000);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setBucketTTL(1000);
		Thread.sleep(1500);
		assertEquals(0, cache.getTotalEntries());
	}
	
	@Test
	public void testSetBucketTTLMoreThanPreviousTTL() throws InterruptedException {
		cache = new CacheController(capacity, 1000);
		cache.setCache("first", "value1");
		cache.setCache("second", "value2");
		cache.setCache("third", "value3");
		cache.setBucketTTL(10000);
		Thread.sleep(2000);
		assertEquals(3, cache.getTotalEntries());
	}
	
	@Test
	public void testSetBucketWithCapacityLessThanOneWithBucketTTLMethod() {
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> new CacheController(-1, 1000),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_CAPACITY_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testSetBucketWithCapacityGreaterThanTwoHundreadWithBucketTTLMethod() {
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> new CacheController(201,15000),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_CAPACITY_NOT_VALID_MESSAGE);
	}
	
	
	
	@Test
	public void testSetCacheWithInvalidKeyWithBucketTTLMethod() {
		cache = new CacheController(14,2000);
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> cache.setCache(" ", "avc"),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_KEY_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testSetCacheWithInvalidKeyAsNullWithBucketTTLMethod() {
		cache = new CacheController(14,2000);
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> cache.setCache(null, "avc"),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_KEY_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testSetCacheWithValueAsNullWithBucketTTLMethod() {
		cache = new CacheController(14,20000);
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> cache.setCache("Test", null),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), CACHE_VALUE_NOT_VALID_MESSAGE);
	}
	
	
	@Test
	public void testSetBucketWithTTLGreaterThan14400000WithBucketTTLMethod() {
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> new CacheController(14, 144000001),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_TTL_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testSetBucketWithTTLLessThanZeroWithBucketTTLMethod() {
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> new CacheController(14, -123),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_TTL_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testSetBucketWhileSettingTTLWithWrongValue() {
		cache = new CacheController(14,20000);
		IllegalArgumentException thrown = assertThrows(
		           IllegalArgumentException.class,
		           () -> cache.setBucketTTL(-100),
		           "Expected doThing() to throw, but it didn't"
		    );
		assertTrue(thrown.getClass().equals(IllegalArgumentException.class));
		assertEquals(thrown.getMessage(), BUCKET_TTL_NOT_VALID_MESSAGE);
	}
	
	@Test
	public void testIsEmptyMethodTrueConditionWithBucketTTLMethod() throws InterruptedException {
		cache = new CacheController(capacity, bucketTTL);
		assertTrue(cache.isEmpty());
	}
	
	@Test
	public void testIsEmptyMethodFalseConditionWithBucketTTLMethod() throws InterruptedException {
		cache = new CacheController(capacity, bucketTTL);
		cache.setCache("first", "value1");
		assertFalse(cache.isEmpty());
	}
}
