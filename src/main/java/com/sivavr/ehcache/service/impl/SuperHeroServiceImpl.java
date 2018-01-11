package com.sivavr.ehcache.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sivavr.ehcache.dao.SuperHeroDAO;
import com.sivavr.ehcache.model.SuperHero;
import com.sivavr.ehcache.service.SuperHeroService;

@Service("superHeroService")
public class SuperHeroServiceImpl implements SuperHeroService {

	@Autowired
	@Qualifier("superHeroDaoImpl")
	SuperHeroDAO superHeroDAOImpl;

	public SuperHeroServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public SuperHero Save(SuperHero superHero) {
		// TODO Auto-generated method stub
		return superHeroDAOImpl.Save(superHero);
	}

	@Override
	public List<SuperHero> findById(Long id) {
		// TODO Auto-generated method stub
		return superHeroDAOImpl.findById(id);
	}

	@Override
	public List<SuperHero> findAll() {
		// TODO Auto-generated method stub
		return superHeroDAOImpl.findAll();
	}

	@Override
	public Long getsIncrement() {
		// TODO Auto-generated method stub
		return superHeroDAOImpl.getsIncrement();
	}

}
