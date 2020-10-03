package com.sats.JavaSimpleInMemoryCache;


import java.util.HashMap;
import java.util.Map;

import com.sats.caching.extern.CacheBucket;
import com.sats.caching.internal.services.CacheController;
/**
 * Unit test for simple App.
 */

public class AppTest {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Running started");
		CacheBucket controller2 = new CacheController(200,10000);
		CacheBucket controller = new CacheController(3,10000);
		String ab = "  ";
		String bc = null;
		String cd = "";
		System.out.println(ab.isBlank());
//		System.out.println(bc.isBlank());
		System.out.println(cd.isBlank());
//		controller.setCache("first", "abc");
//		Thread.sleep(1);
//		controller.setCache("second", "bcd");
//		Thread.sleep(10);
//		controller.setCache("third", "xyz");
//		controller.setCache("fourth", "qwe");
//		System.out.println(controller.getBucketCapacity());
//		controller.setBucketCapacity(5);
//		controller.setCache("five", "12");
//		controller.setCache("six", "12355");
//		controller.setCache("seven", "5323");
//		Thread.sleep(9000);
//		System.out.println(controller.getAll());
//		
//		System.out.println(controller.getBucketCapacity());
//		Thread.sleep(2000);
//		System.out.println(controller.getAll());
//		controller.setCache("first", "1");
//		controller.setCache("second", "1");
//		controller.setCache("third", "1");
//		controller.setCache("four", "1");
//		controller2.setCache("five", "1");
//		controller2.setCache("first", "1");
//		ConcurrentHashMap<String, Cache> xyz = new ConcurrentHashMap<String, Cache>();
//		xyz.put("sats", controller);
//		HashMap<String, Object> amk = new HashMap<String, Object>();
//		for (Map.Entry<String, Cache> entry : xyz.entrySet()) {
//		    String key = entry.getKey().toString();
//		    Cache value = entry.getValue();
//		    amk.put(key, value);
//		}
//		controller2.setCache("third", "3");
//		System.out.println(controller2.getAll());
//		controller2.setCache("Four", "4");
//		System.out.println(controller2.getAll());
//		controller2.setCache("Five", "4");
//		Thread.sleep(15000);
//		System.out.println(controller2.getAll());
//		System.out.println(controller.getAll());
	}

}
