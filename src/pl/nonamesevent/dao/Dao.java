package pl.nonamesevent.dao;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.store.ExecutionContext;
import org.datanucleus.transaction.NucleusTransactionException;

import pl.nonamesevent.model.Category;
import pl.nonamesevent.model.Event;
import pl.nonamesevent.model.EventsList;
import pl.nonamesevent.service.EMFService;
import pl.nonamesevent.utilities.GeoLocationInBoundingCircle;

@NamedNativeQuery(name = "Event.findByLocation", query = "SELECT e FROM Event e WHERE lat < :latMax AND lat > :latMin")
public enum Dao {
	INSTANCE;

	/* ------------------ DAO FOR EVENTS -------------------------- */

	public List<Event> listEvents() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Event m");
		@SuppressWarnings("unchecked")
		List<Event> Events = q.getResultList();
		em.close();
		return Events;
	}

	public void addEvent(Event event) {
		synchronized (this) {
			EntityManager em = null;
			try {

				em = EMFService.get().createEntityManager();
				em.getTransaction().begin();

				
				em.persist(event);
				
				
				ExecutionContext ec = (ExecutionContext) em.getDelegate();
				ec.getTransaction().commit();
				// em.close();
			} catch (NucleusTransactionException e) {
				e.getStackTrace();
				System.out.println("Add event - problem z transakcj¹ : "
						+ e.getMessage() + "  FIX ME!!!!!!!  ");
				em.close();
			} finally {
				// em.close();
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
		Query q = em.createQuery(
				"SELECT e FROM Event e WHERE lat > :latMin AND lat < :latMax",
				Event.class);
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

	/* ------------------ DAO FOR CATEGORY -------------------------- */
	public List<Category> listCategories() {
		EntityManager em = EMFService.get().createEntityManager();

		Query q = em.createQuery("select c from Category c");
		@SuppressWarnings("unchecked")
		List<Category> categories = q.getResultList();
		em.close();
		return categories;
	}

	public void addCategory(Category category) {
		synchronized (this) {
			EntityManager em = null;
			try {
				em = EMFService.get().createEntityManager();
				em.getTransaction().begin();
				em.persist(category);
				ExecutionContext ec = (ExecutionContext) em.getDelegate();
				ec.getTransaction().commit();
				// em.close();
				// em.getTransaction().commit();
			} catch (NucleusTransactionException e) {
				e.getStackTrace();
				System.out.println("Wypieprza blad z transakcj¹ "
						+ e.getMessage() + "  FIX ME!!!!!!!  ");
				em.close();
			} finally {
				// em.close();
			}

		}
	}

	public Category getCategory(Long id) {
		Category result = null;
		EntityManager mgr = EMFService.get().createEntityManager();
		try {
			result = mgr.find(Category.class, id);
		} finally {
			mgr.close();
		}
		if (result == null) {
			System.out.println("No Category returned");

		}
		return result;
	}
	/*
	 * public void deleteContacts() { logger.info("Entering deleteContacts");
	 * EntityManager mgr = EMF.get().createEntityManager(); try { Query q =
	 * mgr.createQuery("DELETE FROM Contact x"); q.executeUpdate(); } finally {
	 * mgr.close(); } logger.info("Exiting deleteContacts"); }
	 */

}
