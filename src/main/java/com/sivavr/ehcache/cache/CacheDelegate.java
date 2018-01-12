package com.sivavr.ehcache.cache;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sivavr.ehcache.dao.SuperHeroDAO;
import com.sivavr.ehcache.model.SuperHero;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

@Repository("cacheDelegate")
public final class CacheDelegate {
	private static final Logger log = Logger.getLogger(CacheDelegate.class);
	private static final String EHCACHE_CONFIG = "C:/Users/shivakumar.m/git/ehcache-spring-hibernate-write-behind/src/main/resources/ehcache.xml";
	private static final String CACHE_NAME = "herosCache";

	private CacheManager manager;
	private Ehcache cache;

	@Autowired
	@Qualifier("superHeroDaoImpl")
	private SuperHeroDAO superHeroDaoImpl;

	public CacheDelegate() {
		log.info("***Initializing Cache Delegate Class***");
		manager = CacheManager.create(EHCACHE_CONFIG);
		cache = manager.getCache(CACHE_NAME);
		log.info("Cahe is:" + cache.toString() + cache);
		SuperHeroCacheWriter writer = new SuperHeroCacheWriter(cache);
		cache.registerCacheWriter(writer);
		SuperHeroCacheLoader loader = new SuperHeroCacheLoader();
		cache.registerCacheLoader(loader);
	}

	/**
	 * Method that removes a certain object (by key) from cache. Please note
	 * that this method will not log until the object gets evicted from data
	 * store.
	 * 
	 * @param key
	 *            The cache object key
	 */

	public void removeElementFormCache(Object key) {
		if (!cache.remove(key)) {
			throw new RuntimeException("Could Not remove key-value from Cache");
		}
	}

	/**
	 * Method that adds a new hero object in Cache
	 * 
	 * @param hero
	 *            model object instance
	 */
	public void addElementToCache(SuperHero hero) {
		cache.put(new Element(hero.getId(), hero));
	}

	/**
	 * Method that adds a new hero object in CacheWriter
	 * 
	 * @param hero
	 *            model object instance
	 */

	public void addElementToCacheWriter(SuperHero hero) {
		log.info("*** CacheDelegate.addElementToCacheWriter() ***");
		log.info("before::cache size = " + cache.getSize());
		// get key
		log.info("Next Increment:" + superHeroDaoImpl.getsIncrement());
		long key = superHeroDaoImpl.getsIncrement() + 1;
		hero.setId(key);
		log.info("*** CacheDelegateputWithWriter" + hero.getMovie() + "," + hero.getName() + " ***");
		// put caching
		cache.putWithWriter(new Element(hero.getId(), hero));
		log.info("after::cache size = " + cache.getSize());
	}

	/**
	 * Method that get a hero object in Cache
	 * 
	 * @param hero
	 *            model object instance
	 */
	public SuperHero getElementFromCache(Long key) {
		log.info("*** CacheDelegate.getElementFromCache() ***");
		log.info("cache size = " + cache.getSize());
		return (SuperHero) cache.get(key).getObjectValue();
	}

	/**
	 * Method that get a hero object in CacheLoader
	 * 
	 * @param hero
	 *            model object instance
	 */
	public SuperHero getElementFromCacheLoader(Long key) {
		log.info("*** CacheDelegate.getElementFromCacheLoader() ***");
		log.info("cache size = " + cache.getSize() + ",cache is:" + cache.toString() + cache);
		return (SuperHero) cache.getWithLoader(key, null, null).getObjectValue();
	}

	/**
	 * Method that removes all Elements in Cache
	 */
	public void removeAllElementsInCache() {
		cache.removeAll();
	}

	/**
	 * Find all the heros
	 * 
	 * @return The list
	 */
	public List<SuperHero> findAll() {
		log.info("*** CacheDelegate.findAll() ***");
		log.info(superHeroDaoImpl);
		return superHeroDaoImpl.findAll();
	}

	/**
	 * Simulates an exception during a cache method execution. Invalid key.
	 */
	public void generateException() {
		cache.getCacheExceptionHandler().onException(cache, 100, new CacheException());
	}
}
