package com.sats.JavaSimpleInMemoryCache;


import com.sats.caching.extern.CacheBucket;
import com.sats.caching.internal.services.CacheController;
/**
 * Unit test for simple App.
 */
class abc {
	abc(){};
}

public class AppTest {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Running started");
		CacheBucket controller2 = new CacheController(10,10000);
		CacheBucket controller = new CacheController(12,10000);
		controller.setCache("first", "1");
		controller.setCache("second", "1");
		controller.setCache("third", "1");
		controller.setCache("four", "1");
		controller2.setCache("five", "1");
		controller2.setCache("first", "1");
//		ConcurrentHashMap<String, Cache> xyz = new ConcurrentHashMap<String, Cache>();
//		xyz.put("sats", controller);
//		HashMap<String, Object> amk = new HashMap<String, Object>();
//		for (Map.Entry<String, Cache> entry : xyz.entrySet()) {
//		    String key = entry.getKey().toString();
//		    Cache value = entry.getValue();
//		    amk.put(key, value);
//		}
		controller2.setCache("third", "3");
		System.out.println(controller2.getAll());
		controller2.setCache("Four", "4");
		System.out.println(controller2.getAll());
		controller2.setCache("Five", "4");
		Thread.sleep(15000);
		System.out.println(controller2.getAll());
		System.out.println(controller.getAll());
	}

}
