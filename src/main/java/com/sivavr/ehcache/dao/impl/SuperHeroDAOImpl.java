package com.sivavr.ehcache.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.sivavr.ehcache.dao.SuperHeroDAO;
import com.sivavr.ehcache.model.SuperHero;

@Repository("superHeroDaoImpl")
public class SuperHeroDAOImpl implements SuperHeroDAO {
	private static final Logger LOGGER = Logger.getLogger(SuperHeroDAOImpl.class);
	
	private SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	public SuperHeroDAOImpl() {

	}
	@Autowired
	public SuperHeroDAOImpl(SessionFactory sessionFactory) {		
		this.sessionFactory = sessionFactory;
	}

	@Override
	@CacheEvict(value = "herosCache", allEntries = true)
	public SuperHero Save(SuperHero superHero) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.persist(superHero);
		tx.commit();
		session.close();		
		return superHero;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value = "herosCache", key = "#id")
	public SuperHero findById(Long id) {
		LOGGER.info("*** Accessing Dao Layer: SuperHeroDAOImpl.findById() ***");		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("from SuperHero WHERE id=" + id);
		List<SuperHero> result = q.list();
		tx.commit();
		session.close();
		return (SuperHero)result.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable("herosCache")
	public List<SuperHero> findAll() {		
		LOGGER.info("*** Accessing Dao Layer: SuperHeroDAOImpl.findAll() ***");
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<SuperHero> heroList = session.createCriteria(SuperHero.class).list();
		tx.commit();
		session.close();
		return heroList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Long getsIncrement() {		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("select count(*) from SuperHero");
		List result = q.list();
		Long increment = new Long(0);
		if (result.size() == 1) {
			increment = Long.parseLong(result.get(0).toString());
		}
		tx.commit();
		session.close();
		return increment;
	}

}
