package com.sats.caching.internal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

public class BucketTest {
	
	Bucket<Object,Object> bucket;
	
	@Test
	public void testBucketCreation() {
		bucket = new Bucket<>();
		List<String> index = bucket.getIndex();
		assertNotNull(bucket);
		assertEquals(index, bucket.getIndex());
		
	}
	
	
}
