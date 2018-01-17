package com.sivavr.ehcache.cache;

import java.util.Properties;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.CacheWriterFactory;

public class SuperHeroCacheWriterFactory extends CacheWriterFactory {
	@Override
	public CacheWriter createCacheWriter(Ehcache cache, Properties properties) {		
		return new SuperHeroCacheWriter(cache);
	}
}
