package pl.nonamesevent.dao;

import java.util.ArrayList;
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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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

	public Event getEvent(int id) {
		EntityManager em = EMFService.get().createEntityManager();
		Key key = KeyFactory.createKey(Event.class.getSimpleName(), id);
		Event e = em.find(Event.class, key);
		System.out.println("Found Event : " + e.toString());
		return e;
	}

	public void updateEvent(Event e) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			em.persist(e);
			System.out.println("after persist");
			em.refresh(e);
		} catch (NucleusTransactionException nte) {
			nte.getStackTrace();
			System.out.println("updateEvent event - problem z transakcj¹ : "
					+ nte.getMessage() + "  FIX ME!!!!!!!  ");
			em.close();
		} finally {
			//em.close();
		}
		System.out.println("Refresh done");
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Event Event = em.find(Event.class, id);
			em.remove(Event);
		} catch (NucleusTransactionException e) {
			e.getStackTrace();
			System.out.println("remove event - problem z transakcj¹ : "
					+ e.getMessage() + "  FIX ME!!!!!!!  ");
			em.close();
		} finally {
			//em.close();
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

		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		Query q = em
				.createQuery("SELECT e FROM Event e WHERE lat > :latMin AND lat < :latMax");
		q.setParameter("latMin", boundingCords.get("latMin"));
		q.setParameter("latMax", boundingCords.get("latMax"));

		System.out.println("# latMin : " + boundingCords.get("latMin"));
		System.out.println("# latMax : " + boundingCords.get("latMax"));
		System.out.println("# lonMin : " + boundingCords.get("lonMin"));
		System.out.println("# lonMax : " + boundingCords.get("lonMax"));
		List<Event> tmp = q.getResultList();

		List<Event> list = new ArrayList<Event>();
		for (Event e : tmp) {
			if (e.getLng() > boundingCords.get("lonMin")
					&& e.getLng() < boundingCords.get("lonMax")) {
				list.add(e);
			}
		}
		System.out.println("size of list : " + list.size());
		for (Event e : list) {
			System.out.println(e.toString());

		}
		// EventsList events = new EventsList();
		// events.setEvents(Dao.INSTANCE.getEvents());
		// System.out.println("A ilosc wszystkich w bazie to : " +
		// events.size());

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
