package com.sivavr.ehcache.dao.cached;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sivavr.ehcache.cache.CacheDelegate;
import com.sivavr.ehcache.dao.SuperHeroDAO;
import com.sivavr.ehcache.model.SuperHero;

@Repository("cachedSuperHeroDaoImpl")
public class CahcedSuperHeroDAO implements SuperHeroDAO {

	@Autowired
	@Qualifier("cacheDelegate")
	private CacheDelegate cacheDelegate;

	@Override
	public SuperHero Save(SuperHero superHero) {		
		cacheDelegate.addElementToCacheWriter(superHero);
		return superHero;
	}

	@Override
	public SuperHero findById(Long id) {	
		List<SuperHero> list = (List<SuperHero>) cacheDelegate.getElementFromCacheLoader(id);
		return (SuperHero) list.get(0);
	}

	@Override
	public List<SuperHero> findAll() {	
		return cacheDelegate.findAll();
	}

	@Override
	public Long getsIncrement() {		
		throw new UnsupportedOperationException("Unsupported get getsIncrement()");
	}

}
