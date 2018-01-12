package com.sivavr.ehcache.cache;

import java.util.Properties;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.loader.CacheLoader;
import net.sf.ehcache.loader.CacheLoaderFactory;

public class SuperHeroCacheLoaderFactory extends CacheLoaderFactory {

	@Override
	public CacheLoader createCacheLoader(Ehcache cache, Properties properties) {
		// TODO Auto-generated method stub
		return new SuperHeroCacheLoader();
	}

}
