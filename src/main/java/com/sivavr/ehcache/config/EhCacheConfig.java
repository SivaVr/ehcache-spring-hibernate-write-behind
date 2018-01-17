package com.sivavr.ehcache.config;

import javax.sound.midi.Soundbank;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.CacheConfiguration.CacheLoaderFactoryConfiguration;
import net.sf.ehcache.config.CacheWriterConfiguration;

@Configuration
@EnableCaching
@ComponentScan("com.sivavr.ehcache")
public class EhCacheConfig implements CachingConfigurer {
	final static String CACHE_POLICY = "LRU";

	@Bean(destroyMethod = "shutdown")
	@DependsOn("hibernateTransactionManager")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cacheConfig("herosCache", 1000));
		// TODO add more cache config ex(config.addCache(cacheConfig("test1",
		// 500)))
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	@Bean
	@Override
	@DependsOn("hibernateTransactionManager")
	public org.springframework.cache.CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}

	private CacheConfiguration cacheConfig(String name, long maxEntries) {
		CacheConfiguration config = new CacheConfiguration();
		config.setName(name);
		config.setMaxEntriesLocalHeap(maxEntries);
		config.setEternal(true);
		config.setOverflowToDisk(false);
		config.setMemoryStoreEvictionPolicy(CACHE_POLICY);
		config.cacheWriter(new CacheWriterConfiguration().writeMode(CacheWriterConfiguration.WriteMode.WRITE_BEHIND)
				.maxWriteDelay(3).rateLimitPerSecond(10).notifyListenersOnException(true).writeCoalescing(true)
				.writeBatching(true).writeBatchSize(2).retryAttempts(5).retryAttemptDelaySeconds(5)
				.cacheWriterFactory(new CacheWriterConfiguration.CacheWriterFactoryConfiguration()
						.className("com.sivavr.ehcache.cache.SuperHeroCacheWriterFactory")));
		config.cacheLoaderFactory(
				new CacheLoaderFactoryConfiguration().className("com.sivavr.ehcache.cache.SuperHeroCacheLoaderFactory")
						.properties("type=int;startCounter=10").propertySeparator(";"));
		return config;
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
