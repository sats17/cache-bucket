package com.sats.JavaSimpleInMemoryCache;

import com.sats.main.CacheController;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Running started");
		CacheController controller2 = new CacheController(30000);
		controller2.setCache("first", "1");
		System.out.println(controller2.getAll());
		controller2.setCache("second", "2");
		System.out.println(controller2.getAll());
		controller2.setCache("third", "3");
		System.out.println(controller2.getAll());
		controller2.setCache("Four", "4");
		System.out.println(controller2.getAll());
		controller2.setCache("five", "5");
		System.out.println(controller2.getAll());

	}

}
