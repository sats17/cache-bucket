package com.github.sats17.caching.extern.load.test;

import org.jsmart.zerocode.core.domain.LoadWith;
import org.jsmart.zerocode.core.domain.TestMapping;
import org.jsmart.zerocode.core.domain.TestMappings;
import org.jsmart.zerocode.jupiter.extension.ParallelLoadExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import com.github.sats17.caching.extern.test.CacheBucketIntegrationTest;

import org.junit.platform.runner.JUnitPlatform;

@RunWith(JUnitPlatform.class)
@LoadWith("load_generation.properties")
@ExtendWith({ ParallelLoadExtension.class })
public class CacheBucketLoadTest {

	@Test
	@DisplayName("Testing parrallel load for cache controller")
	@TestMappings({
		@TestMapping(testClass = CacheBucketIntegrationTest.class, testMethod = "perfSetCache"),
		@TestMapping(testClass = CacheBucketIntegrationTest.class, testMethod = "perfGetCache"),
		
	})
	public void testLoadCacheController() {
		
	}
	
}
