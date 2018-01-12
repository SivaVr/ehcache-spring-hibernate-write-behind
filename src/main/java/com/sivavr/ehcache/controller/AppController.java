package com.sivavr.ehcache.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@RequestMapping(value = { "/addNewHero" }, method = RequestMethod.POST)
	@ResponseBody
	public String addHero(SuperHero hero, ModelAndView model) {
		superHeroService.Save(hero);
		return "success";
	}

	@RequestMapping(value = { "/viewHerosById" }, method = RequestMethod.POST)
	@ResponseBody
	public String viewHeroById(SuperHero hero, Model model) {
		System.out.println("Hero id is:" + hero.getId());
		//System.out.println("Hero id is:" + superHeroService.findById(hero.getId()));
		List<SuperHero> heroList = superHeroService.findById(hero.getId());

		return JsonConverter.toJson(heroList);
	}

	@RequestMapping(value = { "/viewHeros" }, method = RequestMethod.POST)
	@ResponseBody
	public String viewHeros(Model model) {
		List<SuperHero> heroList = superHeroService.findAll();
		return JsonConverter.toJson(heroList);
	}
}
