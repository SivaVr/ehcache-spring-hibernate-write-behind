package com.sivavr.ehcache.cache;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sivavr.ehcache.controller.ApplicationContextProvider;
import com.sivavr.ehcache.dao.SuperHeroDAO;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import net.sf.ehcache.loader.CacheLoader;

public class SuperHeroCacheLoader implements CacheLoader {
	private static final Logger LOGGER = Logger.getLogger(CacheLoader.class);

	@Override
	public Object load(Object key, Object argument) throws CacheException {
		// TODO Auto-generated method stub

		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map loadAll(Collection keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(Object key) throws CacheException {
		LOGGER.info("***Super HeroCache Loader-Load(HeroID)" + key.toString() + "***");
		SuperHeroDAO superHeroDaoImpl = (SuperHeroDAO) ApplicationContextProvider.getApplicationContext()
				.getBean("superHeroDaoImpl");
		return superHeroDaoImpl.findById((Long) key);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map loadAll(Collection keys, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheLoader clone(Ehcache cache) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
