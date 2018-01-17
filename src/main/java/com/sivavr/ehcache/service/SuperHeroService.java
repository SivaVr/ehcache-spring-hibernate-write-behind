package com.sivavr.ehcache.service;

import java.util.List;

import com.sivavr.ehcache.model.SuperHero;

public interface SuperHeroService {
	SuperHero Save(SuperHero superHero);

	SuperHero findById(Long id);

	List<SuperHero> findAll();

	Long getsIncrement();
}
