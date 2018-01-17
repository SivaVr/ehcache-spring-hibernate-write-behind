package com.sivavr.ehcache.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.sivavr.ehcach.utils.JsonConverter;
import com.sivavr.ehcache.model.SuperHero;
import com.sivavr.ehcache.service.SuperHeroService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	@Qualifier("superHeroService")
	SuperHeroService superHeroService;
	private static final Logger log = Logger.getLogger(AppController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@RequestMapping(value = { "/addNewHero" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String addHero(HttpServletRequest request, HttpServletResponse response, SuperHero hero) {
		log.info("****Invoking Service Method: Save()****");
		superHeroService.Save(hero);
		return "success";
	}

	@RequestMapping(value = { "/viewHerosById" }, method = RequestMethod.POST)

	@ResponseBody
	public String viewHeroById(HttpServletRequest request, HttpServletResponse response, SuperHero hero) {
		log.info("****Invoking Service Method: findById()****");
		log.info("Hero id is:" + hero.getId());
		// System.out.println("Hero id is:" +
		// superHeroService.findById(hero.getId()));
		List<SuperHero> heroList = (List<SuperHero>) superHeroService.findById(hero.getId());

		return JsonConverter.toJson(heroList);
	}

	@RequestMapping(value = { "/viewHeros" }, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String viewHeros(HttpServletRequest request, HttpServletResponse response) {
		log.info("****Invoking Service Method: findAll()****");
		List<SuperHero> heroList = superHeroService.findAll();
		return JsonConverter.toJson(heroList);
	}
}
