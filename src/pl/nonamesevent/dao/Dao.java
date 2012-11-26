package pl.nonamesevent.dao;

import java.awt.print.Book;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.datanucleus.store.ExecutionContext;

import pl.nonamesevent.model.Event;
import pl.nonamesevent.model.EventsList;
import pl.nonamesevent.service.EMFService;
import pl.nonamesevent.utilities.GeoLocationInBoundingCircle;

@NamedNativeQuery(name = "Event.findByLocation", query = "SELECT e FROM Event e WHERE lat < :latMax AND lat > :latMin")
public enum Dao {
	INSTANCE;

	public List<Event> listEvents() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Event m");
		@SuppressWarnings("unchecked")
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
				// ev.setLat(lat);
				// ev.setLng(lng);
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
				e.printStackTrace();
				System.out.println("Chujowo ale polecial warning: "
						+ e.getMessage() + "  FIX ME!!!!!!!  ");
			}

		}
	}

	public List<Event> getEvents() {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select e from Event e");
		@SuppressWarnings("unchecked")
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Event> getEventsInGivenCircle(Double lat, Double lon,
			int distance) {
		// String query =
		// GeoLocationInBoundingCircle.createSQLQueryBasedOnCurrentLocation(lat,
		// lon, distance, null, null, null, null);
		// EntityManager em = EMFService.get().createEntityManager();
		// Query q = em.createQuery(query);
		EntityManager em = EMFService.get().createEntityManager();
		HashMap<String, Double> boundingCords = GeoLocationInBoundingCircle
				.getBoundingCords(lat, lon, distance);

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
		Root<Event> candidate = cq.from(Event.class);
		candidate.alias("e");
		cq.select(candidate);
		// Path titleField = candidate.get("lat");
		// //cq.where(cb.equal(titleField, "Bar Book"));
		// cq.where(cb.lt(titleField, boundingCords.get("latMin")));
		// Query q = em.createQuery(cq);
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Query q = em.createQuery("SELECT e FROM Event e WHERE lat > :latMin AND lat < :latMax",Event.class);
		q.setParameter("latMin", boundingCords.get("latMin"));
		q.setParameter("latMax", boundingCords.get("latMax"));
		
		System.out.println("latMin : " + boundingCords.get("latMin"));
		System.out.println("latMax : " + boundingCords.get("latMax"));
		List<Event> list = q.getResultList();
		System.out.println("size of list : " + list.size());
		for (Event e : list) {
			System.out.println(e.toString());

		}
		EventsList events = new EventsList();
		events.setEvents(Dao.INSTANCE.getEvents());
		System.out.println("A ilosc wszystkich w bazie to : " + events.size());

		return list;
	}
}
