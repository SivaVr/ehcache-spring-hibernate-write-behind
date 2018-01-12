package com.sivavr.ehcache.cache;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sivavr.ehcach.utils.JsonConverter;
import com.sivavr.ehcache.controller.ApplicationContextProvider;
import com.sivavr.ehcache.dao.SuperHeroDAO;
import com.sivavr.ehcache.model.SuperHero;

import net.sf.ehcache.CacheEntry;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import net.sf.ehcache.writer.CacheWriter;
import net.sf.ehcache.writer.writebehind.operations.SingleOperationType;

public class SuperHeroCacheWriter implements CacheWriter {
	private static final Logger log = Logger.getLogger(CacheWriter.class);
	private final Ehcache cache;
	@Autowired
	@Qualifier("superHeroDaoImpl")
	private SuperHeroDAO superHeroDaoImpl;

	public SuperHeroCacheWriter(Ehcache cache) {
		// TODO Auto-generated constructor stub
		this.cache = cache;
	}

	@Override
	public CacheWriter clone(Ehcache cache) throws CloneNotSupportedException {
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
	public void write(Element element) throws CacheException {
		// TODO Auto-generated method stub
		log.info("***Super Hero Cache Writer write:" + element.getObjectValue() + "***");
		// SuperHeroDAO superHeroDaoImpl = (SuperHeroDAO)
		// ApplicationContextProvider.getApplicationContext()
		// .getBean("superHeroDaoImpl");
		SuperHero inserted = superHeroDaoImpl.Save((SuperHero) element.getObjectValue());
		log.info("***Cache Size : " + cache.getSize());
		log.info("***Super Hero Cache Writer write to Cache***");
		log.info("***Super Hero Cache Writer writed hero id:" + inserted.getId() + " AND Name:+"
				+ inserted.getName() + " ***");
		cache.put(new Element(inserted.getId(), inserted));
		log.info("***Cache Size After inserted : " + cache.getSize());

	}

	@Override
	public void writeAll(Collection<Element> elements) throws CacheException {
		// TODO Auto-generated method stub
		log.info("***Super Hero Cache Writer write All:" + JsonConverter.toJson(elements) + "***");
		for (Element element : elements) {
			write(element);
		}
	}

	@Override
	public void delete(CacheEntry entry) throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Collection<CacheEntry> entries) throws CacheException {
		// TODO Auto-generated method stub

	}

	@Override
	public void throwAway(Element element, SingleOperationType operationType, RuntimeException e) {
		// TODO Auto-generated method stub

	}

}
