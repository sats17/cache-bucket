package com.sats.caching.extern;

import java.util.HashMap;


public interface Cache {

	public void setCache(String key,Object value);
	
	public void setCache(String key,Object value,Boolean canCacheExpire);
	
	public Object getCache(String key);
	
	public HashMap<Object, Object> getAll();
	
	public void clear(String key);
	
	public void clear();
	
}
