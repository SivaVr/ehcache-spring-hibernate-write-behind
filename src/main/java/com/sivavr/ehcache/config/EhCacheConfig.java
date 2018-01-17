package com.sivavr.ehcache.config;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.CacheConfiguration.CacheLoaderFactoryConfiguration;
import net.sf.ehcache.config.CacheWriterConfiguration;


@Configuration
@EnableCaching
@ComponentScan("com.sivavr.ehcache")
public class EhCacheConfig implements CachingConfigurer {
	final static String CACHE_POLICY = "LRU";

	/**
	 * Configure EhCache Manager.
	 */
	@Bean(destroyMethod = "shutdown")
	//@DependsOn("hibernateTransactionManager")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		String cacheWriterClass = "", cacheLoaderClass = "";
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		cacheWriterClass = "com.sivavr.ehcache.cache.SuperHeroCacheWriterFactory";
		cacheLoaderClass = "com.sivavr.ehcache.cache.SuperHeroCacheLoaderFactory";
		config.addCache(cacheConfig("herosCache", 1000, cacheWriterClass, cacheLoaderClass));
		// add more cache by using config.addCache(cacheConfig("test",500,writerClass,loaderClass))
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Bean
	@Override
	//@DependsOn("hibernateTransactionManager")
	public org.springframework.cache.CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}

	@SuppressWarnings("deprecation")
	private CacheConfiguration cacheConfig(String name, long maxEntries, String writerClass, String loaderClass) {
		CacheConfiguration config = new CacheConfiguration();
		config.setName(name);
		config.setMaxEntriesLocalHeap(maxEntries);
		config.setEternal(true);
		config.setOverflowToDisk(false);
		config.setMemoryStoreEvictionPolicy(CACHE_POLICY);
		config.cacheWriter(cacheWriterConfig(writerClass));
		config.cacheLoaderFactory(cacheLoaderFactoryConfiguration(loaderClass));
		return config;
	}

	private CacheWriterConfiguration cacheWriterConfig(String className) {
		return new CacheWriterConfiguration().writeMode(CacheWriterConfiguration.WriteMode.WRITE_BEHIND)
				.maxWriteDelay(3).rateLimitPerSecond(10).notifyListenersOnException(true).writeCoalescing(true)
				.writeBatching(true).writeBatchSize(2).retryAttempts(5).retryAttemptDelaySeconds(5).cacheWriterFactory(
						new CacheWriterConfiguration.CacheWriterFactoryConfiguration().className(className));
	}

	private CacheLoaderFactoryConfiguration cacheLoaderFactoryConfiguration(String className) {
		return new CacheLoaderFactoryConfiguration().className(className).properties("type=int;startCounter=10")
				.propertySeparator(";");
	}

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		// TODO Auto-generated method stub
		return null;
	}
}
