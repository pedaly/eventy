package pl.nonamesevent.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.nonamesevent.dao.Dao;
import pl.nonamesevent.model.Category;
import pl.nonamesevent.model.Event;

@Controller
public class EventController {

	public static List<String> wojewodztwa = Arrays.asList("Woj dolnoœl¹skie",
			"Woj kujawsko-pomorskie", "Woj lubelskie", "Woj ³ódzkie",
			"Woj ma³opolskie", "Woj mazowieckie", "Woj opolskie",
			"Woj podkarpackie", "Woj podlaskie", "Woj pomorskie",
			"Woj œl¹skie", "Woj œwiêtokrzyskie", "Woj warmiñsko-mazurskie",
			"Woj wielkopolskie", "Woj zachodniopomorskie");
	
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(EventController.class);

	@RequestMapping(value = "/eventsList", method = RequestMethod.GET)
	public ModelAndView home() {
		List<Event> events = Dao.INSTANCE.getEvents();
		ModelAndView mav = new ModelAndView("eventsList");
		mav.addObject("events", events);
		return mav;
	}

	@RequestMapping(value = "/event/{id}")
	public ModelAndView showEvent(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("singleEvent");
		Event e = Dao.INSTANCE.getEvent(id);
		mav.addObject("event", e);
		return mav;
	}

	@RequestMapping(value = "/event/{id}/delete")
	public ModelAndView deleteEvent(@PathVariable Long id) {
		Dao.INSTANCE.remove(id);
		System.out.println("Deleting event " + id);

		return new ModelAndView(new RedirectView("/eventsList"));
	}

	@RequestMapping(value = "/event/{id}/edit", method = RequestMethod.GET)
	public ModelAndView editEvent(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("addEvent_form");
		mav.addObject(Dao.INSTANCE.getEvent(id));
		List<Category> categories = Dao.INSTANCE.listCategories();
		mav.addObject("categories", categories);
		mav.addObject("wojewodztwa", wojewodztwa);
		return mav;
	}

	@RequestMapping(value = "/event/{id}/edit", method = RequestMethod.POST)
	public ModelAndView postEditEvent(@ModelAttribute("event") Event e) {

		Dao.INSTANCE.updateEvent(e);
		return new ModelAndView(
				new RedirectView("/event/" + e.getId()));
	}

	// ----------------------------- add Event
	// -----------------------------------
	@RequestMapping(value = "/admin/addEvent", method = RequestMethod.GET)
	public ModelAndView addEvent() {

		List<Category> categories = Dao.INSTANCE.listCategories();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("event", new Event());
		model.put("categories", categories);
		model.put("wojewodztwa", wojewodztwa);
		return new ModelAndView("addEvent_form", model);

	}
	


	@RequestMapping(value = " /admin/addEvent", method = RequestMethod.POST)
	public ModelAndView postAddEvent(HttpServletRequest request,
			@ModelAttribute(value = "event")  Event event,
			BindingResult result, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();


//		if (result.hasErrors()) {
//			mav.addObject("event", event);
//			mav.setViewName("addEvent_form");
//			return mav;
//		}

		System.out.println(event.toString());
		Dao.INSTANCE.addEvent(event);
		System.out.println("submit value : " + request.getParameter("submit"));
		String submitValue = request.getParameter("submit");
		if (submitValue.equalsIgnoreCase("true")) {
			System.out.println("Zapisz i dodaj kolejne ");
			return new ModelAndView(new RedirectView("addEvent"));

		} else {
			System.out.println("Zapisz i wróc");
			return new ModelAndView(new RedirectView("/eventsList"));
		}
	}
	// -------------------------------------------------------------------------------------
	// -------------------- OLD saveEvent--------------------------------
	// @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	// public String save(@ModelAttribute(value = "event") Event event,
	// HttpServletRequest request, BindingResult result, HttpServletResponse
	// response) {
	//
	// ModelAndView mav = new ModelAndView();
	//
	// if (result.hasErrors()) {
	// mav.addObject("event", event);
	// return "redirect: addEvent";
	// }
	// System.out.prLongln(event.toString());
	// Dao.INSTANCE.addEvent(event);
	// System.out.prLongln("submit value : " + request.getParameter("submit"));
	// String submitValue = request.getParameter("submit");
	// if(submitValue.equalsIgnoreCase("true")){
	// System.out.prLongln("Zapisz i dodaj kolejne ");
	// mav.setViewName("addEvent");
	// return "redirect: addEvent";
	//
	// }else{
	// System.out.prLongln("Zapisz i wróc");
	// mav.setViewName("eventsList");
	// return "redirect: eventsList";
	// }
	// }
	// --------------------------------------------------------------------------
}