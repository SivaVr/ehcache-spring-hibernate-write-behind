package com.sivavr.ehcache.dao;

import java.util.List;

import com.sivavr.ehcache.model.SuperHero;

public interface SuperHeroDAO {

	SuperHero Save(SuperHero superHero);

	List<SuperHero> findById(Long id);

	List<SuperHero> findAll();
	
	Long getsIncrement();
}
