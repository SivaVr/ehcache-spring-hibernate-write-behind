package com.sivavr.ehcache.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
	private static ApplicationContext ctx;
	private static final Logger LOGGER = Logger.getLogger(ApplicationContextProvider.class);

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

	public static ApplicationContext getApplicationContext() {
		LOGGER.info("ApplicationContext:" + ctx);
		return ctx;
	}

}
