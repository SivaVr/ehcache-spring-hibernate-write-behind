package com.sivavr.ehcache.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sivavr.ehcache.dao.SuperHeroDAO;
import com.sivavr.ehcache.model.SuperHero;
import com.sivavr.ehcache.service.SuperHeroService;


@Service("superHeroService")
public class SuperHeroServiceImpl implements SuperHeroService {
	private static final Logger log = Logger.getLogger(SuperHeroServiceImpl.class);
	@Autowired
	@Qualifier("cachedSuperHeroDaoImpl")
	SuperHeroDAO cachedSuperHeroDaoImpl;

	public SuperHeroServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public SuperHero Save(SuperHero superHero) {
		// TODO Auto-generated method stub
		log.info("*** Accessing heroService Save() ***");
		return cachedSuperHeroDaoImpl.Save(superHero);
	}

	@Override
	public List<SuperHero> findById(Long id) {
		// TODO Auto-generated method stub
		log.info("*** Accessing heroService findById() ***");
		return (List<SuperHero>)cachedSuperHeroDaoImpl.findById(id);
	}

	@Override
	public List<SuperHero> findAll() {
		// TODO Auto-generated method stub
		log.info("*** Accessing heroService findAll() ***");
		return cachedSuperHeroDaoImpl.findAll();
	}

	@Override
	public Long getsIncrement() {
		// TODO Auto-generated method stub
		return cachedSuperHeroDaoImpl.getsIncrement();
	}

}
