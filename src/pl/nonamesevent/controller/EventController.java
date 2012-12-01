package pl.nonamesevent.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory
			.getLogger(EventController.class);


	@RequestMapping(value = "/eventsList", method = RequestMethod.GET)
	public ModelAndView home() {
		System.out.println("/manageEvents");
		List<Event> events = Dao.INSTANCE.getEvents();
		for (int i = 0; i < events.size(); i++) {
			System.out.println(events.get(i).toString());
		}
		ModelAndView mav = new ModelAndView("eventsList");
		
	
		mav.addObject("events", events);

		return mav;
	}
	
	@RequestMapping(value = "/event/{id}")
	public ModelAndView showEvent(@PathVariable int id){
		ModelAndView mav = new ModelAndView("singleEvent");
		Event e = Dao.INSTANCE.getEvent(id);
		mav.addObject("event",e);
		return mav;
	}
	@RequestMapping(value = "/event/{id}/delete")
	public ModelAndView deleteEvent(@PathVariable int id){
		Dao.INSTANCE.remove(id);
		List<Event> events = Dao.INSTANCE.getEvents();
		ModelAndView mav = new ModelAndView("eventsList");
		mav.addObject("events", events);
		return mav;
	}
	@RequestMapping(value = "/event/{id}/edit")
	public ModelAndView editEvent(@PathVariable int id){
		ModelAndView mav = new ModelAndView("addEvent_form");
		mav.addObject(Dao.INSTANCE.getEvent(id));
		return mav;
	}
	@RequestMapping(value = "/event/{id}/edit", method = RequestMethod.PUT)
	public ModelAndView postEditEvent(@ModelAttribute("event") Event e){
		System.out.println("Editing event PUT");
		Dao.INSTANCE.updateEvent(e);
		return new ModelAndView(new RedirectView("/event/"+e.getKey().getId()));
	}
	
	
//----------------------------- add Event -----------------------------------
	@RequestMapping(value = "/admin/addEvent", method = RequestMethod.GET)
	public ModelAndView addEvent(@ModelAttribute("event") Event event,
			BindingResult result) {
		ModelAndView mav = new ModelAndView("addEvent_form");
		List<Category> categories = Dao.INSTANCE.listCategories();
		mav.addObject("categories", categories);
		return mav;
	}
	
	@RequestMapping(value=" /admin/addEvent", method = RequestMethod.POST)
	public ModelAndView postAddEvent(HttpServletRequest request,
			@ModelAttribute(value = "event") Event event,
			BindingResult result, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.addObject("event", event);
			mav.setViewName("addEvent_form");
			return mav;
		}
		System.out.println(event.toString());
		Dao.INSTANCE.addEvent(event);
		System.out.println("submit value : " + request.getParameter("submit"));
		String submitValue = request.getParameter("submit");
		if(submitValue.equalsIgnoreCase("true")){
			System.out.println("Zapisz i dodaj kolejne ");
			return new ModelAndView(new RedirectView("addEvent"));
			
		}else{
			System.out.println("Zapisz i wróc");		
			return new ModelAndView(new RedirectView("/eventsList"));
		}
	}
//-------------------------------------------------------------------------------------
// -------------------- OLD saveEvent--------------------------------
//	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
//	public String save(@ModelAttribute(value = "event") Event event,
//			HttpServletRequest request, BindingResult result, HttpServletResponse response) {
//		
//		ModelAndView mav = new ModelAndView();
//		
//		if (result.hasErrors()) {
//			mav.addObject("event", event);
//			return "redirect: addEvent";
//		}
//		System.out.println(event.toString());
//		Dao.INSTANCE.addEvent(event);
//		System.out.println("submit value : " + request.getParameter("submit"));
//		String submitValue = request.getParameter("submit");
//		if(submitValue.equalsIgnoreCase("true")){
//			System.out.println("Zapisz i dodaj kolejne ");
//			mav.setViewName("addEvent");
//			return "redirect: addEvent";
//			
//		}else{
//			System.out.println("Zapisz i wróc");
//			mav.setViewName("eventsList");
//			return "redirect: eventsList";
//		}
//	}
//--------------------------------------------------------------------------
}