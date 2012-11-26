package pl.nonamesevent.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.nonamesevent.dao.Dao;
import pl.nonamesevent.model.Event;
import pl.nonamesevent.model.EventsList;
import pl.nonamesevent.model.UserContext;

@Controller
@RequestMapping(value = "/getEventsFromLocation")
public class EventWSControler {

	
	@RequestMapping(method= RequestMethod.POST)
	public @ResponseBody EventsList getEventsFromLocation(@RequestBody UserContext userContext){
		UserContext userContext1 = new UserContext();
		List<Event> list = Dao.INSTANCE.getEventsInGivenCircle(userContext1.getLat(), userContext1.getLon(), userContext1.getSearchRadius());
		
		System.out.print("===================== Events ============================");
		for(Event a : list){
			System.out.println(a.toString());
		}
		System.out.print("=========================================================");
//		System.out.print(userContext.toString());
		EventsList events = new EventsList();
		events.setEvents(Dao.INSTANCE.getEvents());
		for (int i = 0; i < events.size(); i++) {
			System.out.println(events.get(i).toString());
		}
		return events;
	}
	
	@RequestMapping(value="/xml", method=RequestMethod.POST)
	@ResponseBody
	public EventsList getEventsListXML(){

		EventsList events = new EventsList();
		events.setEvents(Dao.INSTANCE.getEvents());
		System.out.println("object : " +events.get(1));
		System.out.println("size : " +events.size());
		
		for (int i = 0; i < events.size(); i++) {
			System.out.println(events.get(i).toString());
		}
		return events;
	}

	@RequestMapping(value="/check", method=RequestMethod.POST)
	@ResponseBody
	public List<Event> check(){
		UserContext userContext1 = new UserContext(null, 51.776338, 19.400800, 1);
		List<Event> list = Dao.INSTANCE.getEventsInGivenCircle(userContext1.getLat(), userContext1.getLon(), userContext1.getSearchRadius());
		
		System.out.print("===================== Events ============================");
		for(Event a : list){
			System.out.println(a.toString());
		}
		
		return list;
	}
}
