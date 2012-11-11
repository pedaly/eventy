package pl.nonamesevent.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import dao.Dao;

@Controller
@RequestMapping(value = "/getEventsFromLocation")
public class EventWSControler {

	
	@RequestMapping(method= RequestMethod.POST)
	public @ResponseBody EventsList getEventsFromLocation(@RequestBody UserContext userContext){
		
		System.out.print(userContext.toString());
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
		for (int i = 0; i < events.size(); i++) {
			System.out.println(events.get(i).toString());
		}
		return events;
	}
}
