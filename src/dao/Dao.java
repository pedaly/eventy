package dao;

import javax.persistence.EntityManager;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.datanucleus.store.ExecutionContext;

import pl.nonamesevent.model.Event;
import pl.nonamesevent.service.EMFService;

public enum Dao {
	INSTANCE;

	public List<Event> listEvents() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Event m");
		List<Event> Events = q.getResultList();
		return Events;
	}

	public void add(String city, Date dateOfEvent, String desc, String lat,
			String lng, String manager, String phone, String skype,
			String street, String symbol, String title) {
		synchronized (this) {
			EntityManager em = null;
			try {

				em = EMFService.get().createEntityManager();
				em.getTransaction().begin();
				Event ev = new Event();
				ev.setCity(city);
				ev.setTitle(title);
				ev.setDateOfEvent(dateOfEvent);
				ev.setDescription(desc);
				ev.setLat(lat);
				ev.setLng(lng);
				ev.setPhone(phone);
				ev.setSkype(skype);
				ev.setStreetAndNumber(street);
				em.persist(ev);
				ExecutionContext ec = (ExecutionContext) em.getDelegate();
				ec.getTransaction().commit();

			} catch (Exception e) {
				em.close();
				System.out.println("Chujowo ale polecial warning: "
						+ e.getMessage() + "  FIX ME!!!!!!!  ");
			}

		}
	}

	public void add(Event event) {
		synchronized (this) {
			EntityManager em = null;
			try {

				em = EMFService.get().createEntityManager();
				em.getTransaction().begin();

				em.persist(event);
				ExecutionContext ec = (ExecutionContext) em.getDelegate();
				ec.getTransaction().commit();

			} catch (Exception e) {
				em.close();
				System.out.println("Chujowo ale polecial warning: "
						+ e.getMessage() + "  FIX ME!!!!!!!  ");
			}

		}
	}

	public List<Event> getEvents() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Event e");

		List<Event> Events = q.getResultList();
		return Events;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Event Event = em.find(Event.class, id);
			em.remove(Event);
		} finally {
			em.close();
		}
	}
}
