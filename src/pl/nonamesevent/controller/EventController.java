package pl.nonamesevent.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.nonamesevent.model.Event;
import dao.Dao;

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
		return mav;
	}

	@RequestMapping(value = "/addEvent", method = RequestMethod.GET)
	public ModelAndView addEvent(@ModelAttribute("event") Event event,
			BindingResult result) {
		return new ModelAndView("addEvent");
	}

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute(value = "event") Event event,
			BindingResult result) {

		ModelAndView mav = new ModelAndView("addEvent");

		if (result.hasErrors()) {
			mav.addObject("event", event);
			return mav;
		}
		System.out.println(event.toString());
		Dao.INSTANCE.add(event);

		return new ModelAndView("home");
	}
}
