package pl.nonamesevent.controller;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.tools.util.Logging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pl.nonamesevent.dao.Dao;
import pl.nonamesevent.model.Event;
import pl.nonamesevent.utilities.EventsList;
import pl.nonamesevent.utilities.UserContext;

@Controller
@RequestMapping(value = "/WSgetEventList", method=RequestMethod.GET)
public class EventWSControler {

	private Logger logger = Logger.getLogger(getClass().getName());
	 
	 
	@RequestMapping(method= RequestMethod.POST)
	public @ResponseBody List<Event> getEventsFromLocation(@RequestBody UserContext userContext){
		UserContext userContext1 = new UserContext();
		logger.log(Level.ALL, " #####Log " + userContext.toString());
		logger.warning("##########Warning" + userContext.toString());
		List<Event> list = Dao.INSTANCE.getEventsInGivenCircle(userContext.getLat(), userContext.getLon(), userContext.getSearchRadius());
		logger.warning("##########Warning Lista - " + list.toString());
		System.out.print("===================== Events ============================");
		for(Event a : list){
			System.out.println(a.toString());
		}
		System.out.print("=========================================================");

		
		return list;
	}
	
	@RequestMapping(value="/gson", method= RequestMethod.POST)
	public @ResponseBody String gsonCheck(@RequestBody UserContext userContext1){
		//UserContext userContext1 = new UserContext(null, 51.75934,19.4561, 2);
		logger.log(Level.ALL, " #####Log " + userContext1.toString());
		logger.warning("##########Warning" + userContext1.toString() + " //  " + userContext1.getLon());
		List<Event> list = Dao.INSTANCE.getEventsInGivenCircle(userContext1.getLat(), userContext1.getLon(), userContext1.getSearchRadius());
		EventsList events = new EventsList();
		Gson gson = new Gson();
		events.setEvents(list);
		String result = gson.toJson(list);
		logger.warning("##########Warning Lista - " + list.toString());
		
		
		return result;
	}
	
	@RequestMapping(value="/check", method=RequestMethod.POST)
	@ResponseBody
	public List<Event> check(){
		List<String> categories = new ArrayList<String>();
		categories.add("kat1");
		UserContext userContext1 = new UserContext(categories, 51.75934,19.4561, 50);
		//--testing
		Gson gsonTest = new Gson();
		String test = gsonTest.toJson(userContext1);
		System.out.println("GSON -> " + test);
		//--------
		
		logger.log(Level.ALL, " #####Log " + userContext1.toString());
		logger.warning("##########Warning" + userContext1.toString() + " //  " + userContext1.getLon());
		List<Event> list = Dao.INSTANCE.getEventsInGivenCircle(userContext1.getLat(), userContext1.getLon(), userContext1.getSearchRadius());
		
		System.out.println("===================== FOUND Events ============================");
		for(Event a : list){
			System.out.println(a.toString());
		}
		Gson gson = new Gson();
		String result = gson.toJson(list);
		System.out.println("Events : " + result);
		
		logger.warning("##########Warning Lista - " + list.toString());
		
		Type collectionType = new TypeToken<List<Event>>(){}.getType();
		UserContext fromJson = gson.fromJson(result, collectionType);
		System.out.println("Object lat " + fromJson.getLat());
		return list;
	}
}
