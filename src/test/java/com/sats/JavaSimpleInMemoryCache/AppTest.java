package com.sats.JavaSimpleInMemoryCache;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.sats.caching.extern.CacheBucket;
import com.sats.caching.internal.services.CacheController;
/**
 * Unit test for simple App.
 */

public class AppTest {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Running started");  
//		ConcurrentMap<String, Object> ab = new ConcurrentHashMap<>(7);
//		ab.put("ax", "assas");
//		ab.put("asf", "asfas");
//		ab.remove("bwas");
//		System.out.println("mission ok");
//		ConcurrentMap<String, Object> temp = new ConcurrentHashMap<>(2);
//		temp.putAll(ab);
//		System.out.println("f");
//		System.out.println(temp);
//		System.out.println(ab);
//		ab = null;
//		System.out.println("s");
//		System.out.println(temp);
//		System.out.println(ab);
//		ab = temp;
//		System.out.println("t");
//		System.out.println(temp);
//		System.out.println(ab);
//		temp = null;
//		System.out.println("fo");
//		System.out.println(temp);
//		System.out.println(ab);
//		List<String> abv = new ArrayList<>(20);
//		abv.add("first");
//		abv.add("second");
//		abv.add("third");
//		abv.remove(0);
//		abv.remove(0);
//		System.out.println(abv.size());
//		((ArrayList<String>) abv).trimToSize();
//		System.out.println(abv.size());
//		abv.remove(0);
//		System.out.println(abv);
//		abv.remove(0);
//		System.out.println(abv);
//		CacheBucket controller2 = new CacheController(200,10000);
		CacheBucket controller = new CacheController(200,10000);
//		String ab = "  ";
//		String bc = null;
//		String cd = "";
//		System.out.println(ab.isBlank());
////		System.out.println(bc.isBlank());
//		System.out.println(cd.isBlank());
//		controller.setCache("first", "abc");
//		controller.setCache("second", "bcd");
//		controller.setCache("third", "xyz");
//		controller.setCache("fourth", "qwe");
		for(int i = 0; i < 199; i++) {
			String key = "check+"+i;
			int val = i;
			controller.setCache(key, val);
		}
		System.out.println(controller.getAll());
		System.out.println(controller.getCache("check+166"));
		Thread.sleep(999);
		controller.setBucketCapacity(2);
		System.out.println(controller.getAll());
//		System.out.println(controller.getBucketCapacity());
//		System.out.println(controller.getAll());
//		controller.setCache("first", "abc");
//		System.out.println(controller.getAll());
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
//		amk.put("av", "ass");
//		System.out.println(amk.get("aq"));
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
