package pl.nonamesevent.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Redirect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xpath.internal.operations.Mod;

import pl.nonamesevent.dao.Dao;
import pl.nonamesevent.model.Category;
import pl.nonamesevent.model.Event;

@Controller
public class EventController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(EventController.class);

	@RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
	public ModelAndView index() {
		System.out.println("/index");
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/eventsList", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("/manageEvents");
		List<Event> events = Dao.INSTANCE.getEvents();
		for (int i = 0; i < events.size(); i++) {
			System.out.println(events.get(i).toString());
		}
		ModelAndView mav = new ModelAndView("eventsList");
		mav.addObject("events", events);
		
//		Category cat =  new Category();
//		cat.setName("druga kategoria");
//		Dao.INSTANCE.addCategory(cat);

//		Event e = new Event();
//		e.setCity("Mandalay");
//		Dao.INSTANCE.addEvent(e);		
//		e.setCategory(cat);		

		return mav;
	}
	
	@RequestMapping(value = "/event/{id}")
	public ModelAndView showEvent(@PathVariable int id){
		ModelAndView mav = new ModelAndView("singleEvent");
		Event e = Dao.INSTANCE.getEvent(id);
		mav.addObject("event",e);
		return mav;
	}
	@RequestMapping(value = "/addEvent", method = RequestMethod.GET)
	public ModelAndView addEvent(@ModelAttribute("event") Event event,
			BindingResult result) {
		return new ModelAndView("addEvent");
	}

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public String save(@ModelAttribute(value = "event") Event event,
			HttpServletRequest request, BindingResult result, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		
		if (result.hasErrors()) {
			mav.addObject("event", event);
			return "redirect: addEvent";
		}
		System.out.println(event.toString());
		Dao.INSTANCE.addEvent(event);
		System.out.println("submit value : " + request.getParameter("submit"));
		String submitValue = request.getParameter("submit");
		if(submitValue.equalsIgnoreCase("true")){
			System.out.println("Zapisz i dodaj kolejne ");
			mav.setViewName("addEvent");
			return "redirect: addEvent";
			
		}else{
			System.out.println("Zapisz i wróc");
			mav.setViewName("eventsList");
			return "redirect: eventsList";
		}
	
		
	}
}
