package com.sats.caching.internal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BucketTest {
	
	Bucket<Object,Object> bucket;
	
	@BeforeEach
	public void clearTest() {
		bucket = null;
	}
	
	@Test
	public void testBucketCreation() {
		bucket = new Bucket<>(10);
		List<String> index = bucket.getIndex();
		assertNotNull(bucket);
		assertEquals(index, bucket.getIndex());
		
	}
	
	@Test 
	public void testClearByKeyMethod() {
		bucket = new Bucket<>(10);
		bucket.setBucketCapacity(5);
		bucket.setCache("first", "value1");
		bucket.setCache("second", "value2");
		bucket.setCache("third", "value3");
		bucket.clear("second");
		List<String> expected = new ArrayList<>();
		expected.add("first");
		expected.add("third");
		assertEquals(expected, bucket.getIndex());
		assertEquals(2, bucket.getTotalEntries());
	}
	
	@Test
	public void testClearMethod() {
		bucket = new Bucket<>(10);
		bucket.setBucketCapacity(5);
		bucket.setCache("first", "value1");
		bucket.setCache("second", "value2");
		bucket.setCache("third", "value3");
		List<String> expected = new ArrayList<>();
		bucket.clear();
		assertEquals(0, bucket.getTotalEntries());
		assertEquals(expected, bucket.getIndex());
	}
}
