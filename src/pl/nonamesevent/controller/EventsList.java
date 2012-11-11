package pl.nonamesevent.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


import pl.nonamesevent.model.Event;

/**
 * EventList { list<Events>
 * 
 * 
 * }
 * @author Kamil
 * 
 */
public class EventsList {
	private List<Event> events;

	public EventsList() {
		events = new ArrayList<Event>();
	}

	public void addEvent(Event e) {
		events.add(e);
	}

	public Event getEvent(int id) {
		return events.get(id);
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public int size(){
		return events.size();
	}
	public Event get(int i){
		return events.get(i);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventsList other = (EventsList) obj;
		if (events != other.events)
			return false;
		return true;
	}
}
