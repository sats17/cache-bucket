package com.sats.JavaSimpleInMemoryCache;

import java.lang.ModuleLayer.Controller;

import com.sats.main.CacheController;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest  {
	
	public static void main(String[] args) throws InterruptedException {
		CacheController controller = new CacheController(350);
		CacheController controller2 = new CacheController();
		controller.setCache("Hie", "Hello");
		controller2.setCache("asa", "ava");
		System.out.println(controller.getAll());
		System.out.println(controller2.getAll());
		controller.clear();
		System.out.println("After delete");
		System.out.println(controller.getAll());
		System.out.println(controller2.getAll());
		Thread.sleep(500);
		System.out.println(controller2.getAll());
	}
	
	
   
}
