package pl.nonamesevent.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.nonamesevent.dao.Dao;
import pl.nonamesevent.model.Category;
import pl.nonamesevent.model.Event;

@Controller
public class EventController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(EventController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		System.out.println("/index");
		return "home";
	}

	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("/home");
		List<Event> events = Dao.INSTANCE.getEvents();
		for (int i = 0; i < events.size(); i++) {
			System.out.println(events.get(i).toString());
		}
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("events", events);
		
		Category cat =  new Category();
		cat.setName("pierwsza kategoria");
		Dao.INSTANCE.addCategory(cat);
		
		return mav;
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.GET)
	public ModelAndView addEvent(@ModelAttribute("event") Event event,
			BindingResult result) {
		return new ModelAndView("addEvent");
	}

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public String save(@ModelAttribute(value = "event") Event event,
			BindingResult result, HttpServletResponse response) {

		ModelAndView mav = new ModelAndView("addEvent");

		if (result.hasErrors()) {
			mav.addObject("event", event);
			return "redirect: addEvent";
		}
		System.out.println(event.toString());
		Dao.INSTANCE.addEvent(event);
	
		
		return "redirect: addEvent";
	}
}
