package com.sivavr.ehcache.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.sivavr.ehcach.utils.JsonConverter;
import com.sivavr.ehcache.model.SuperHero;
import com.sivavr.ehcache.service.SuperHeroService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	@Qualifier("superHeroService")
	SuperHeroService superHeroService;
	private static final Logger LOGGER = Logger.getLogger(AppController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@RequestMapping(value = { "/addNewHero" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String addHero(HttpServletRequest request, HttpServletResponse response, SuperHero hero) {
		LOGGER.info("****Invoking Service Method: Save()****");
		superHeroService.Save(hero);
		return "success";
	}

	@RequestMapping(value = { "/viewHerosById" }, method = RequestMethod.POST)

	@ResponseBody
	public String viewHeroById(HttpServletRequest request, HttpServletResponse response, SuperHero hero) {
		LOGGER.info("****Invoking Service Method: findById()****");
		LOGGER.info("Hero id is:" + hero.getId());
		List<SuperHero> heroList = new ArrayList<SuperHero>();
		heroList.add(superHeroService.findById(hero.getId()));
		LOGGER.info("Returnable data:" + JsonConverter.toJson(heroList));
		return JsonConverter.toJson(heroList);
	}

	@RequestMapping(value = { "/viewHeros" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String viewHeros(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("****Invoking Service Method: findAll()****");
		List<SuperHero> heroList = superHeroService.findAll();
		LOGGER.info("Returnable data:" + JsonConverter.toJson(heroList));
		return JsonConverter.toJson(heroList);
	}
}
