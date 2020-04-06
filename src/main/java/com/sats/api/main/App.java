package com.sats.api.main;

import java.util.HashMap;

import com.sats.api.controller.CacheController;

public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        HashMap<String, Integer> a = new HashMap<String, Integer>();
        a.put("2",2);
        CacheController controller2 = new CacheController(21);
        controller2.setCache("String", "asfasf");
        controller2.setCache("asf", "asf");
        controller2.setCache("12", "34");
        controller2.setCache("112", a);
        controller2.setCache("116", "34");
        controller2.setCache("156", "34");
        System.out.println(controller2.getCache("156"));
        controller2.clear();
        System.out.println(controller2.getCache("156"));
    }
}
