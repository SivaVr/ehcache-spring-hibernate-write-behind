package com.sivavr.ehcache.cache;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sivavr.ehcache.controller.ApplicationContextProvider;
import com.sivavr.ehcache.dao.SuperHeroDAO;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import net.sf.ehcache.loader.CacheLoader;

public class SuperHeroCacheLoader implements CacheLoader {
	private static final Logger log = Logger.getLogger(CacheLoader.class);

	private SuperHeroDAO superHeroDaoImpl;

	@Override
	public Object load(Object key, Object argument) throws CacheException {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Map loadAll(Collection keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(Object key) throws CacheException {
		// TODO Auto-generated method stub
		log.info("***Super HeroCache Loader-Load(HeroID)" + key.toString() + "***");
		SuperHeroDAO superHeroDaoImpl = (SuperHeroDAO) ApplicationContextProvider.getApplicationContext()
				.getBean("superHeroDaoImpl");
		System.out.println("superHeroDaoImpl from loader:" + superHeroDaoImpl);
		return superHeroDaoImpl.findById((Long) key);
	}

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
