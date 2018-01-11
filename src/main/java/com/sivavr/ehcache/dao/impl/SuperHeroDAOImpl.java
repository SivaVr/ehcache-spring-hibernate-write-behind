package com.sivavr.ehcache.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sivavr.ehcache.dao.SuperHeroDAO;
import com.sivavr.ehcache.model.SuperHero;

@Repository("superHeroDaoImpl")
public class SuperHeroDAOImpl implements SuperHeroDAO {

	@Autowired
	private SessionFactory sessionFactory;
	Session session = null;
	Transaction tx = null;

	public SuperHeroDAOImpl() {

	}

	public SuperHeroDAOImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		this.sessionFactory = sessionFactory;
	}

	@Override
	public SuperHero Save(SuperHero superHero) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.persist(superHero);
		tx.commit();
		session.close();
		// TODO Auto-generated method stub
		return superHero;
	}

	@Override
	public List<SuperHero> findById(Long id) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("from SuperHero WHERE id=" + id);
		List<SuperHero> result = q.list();	
		
		tx.commit();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SuperHero> findAll() {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<SuperHero> heroList = session.createCriteria(SuperHero.class).list();
		tx.commit();
		session.close();
		return heroList;
	}

	@Override
	public Long getsIncrement() {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("select id from super_heros ORDER BY DESC LIMIT 1");
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
