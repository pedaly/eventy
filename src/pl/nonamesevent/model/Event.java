package pl.nonamesevent.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;

@Entity
public class Event {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key key;

	private Double lng;

	private Double lat;

	private String title;

	private String description;

	private Date dateOfEvent;

	private String managerOfEvent;

	private String city;

	private String streetAndNumber;

	private String webpage;

	private String phone;

	private String skype;

	private String postcode;

	private String wojewodztwo;

	private String powiat;



//	@ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
//	@JoinColumn(name = "categoryId")
	@ManyToOne
	private Category category;

	public Event() {
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public String getManagerOfEvent() {
		return managerOfEvent;
	}

	public void setManagerOfEvent(String managerOfEvent) {
		this.managerOfEvent = managerOfEvent;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}

	public String getWebpage() {
		return webpage;
	}

	public void setWebpage(String webpage) {
		this.webpage = webpage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWojewodztwo() {
		return wojewodztwo;
	}

	public void setWojewodztwo(String wojewodztwo) {
		this.wojewodztwo = wojewodztwo;
	}

	public String getPowiat() {
		return powiat;
	}

	public void setPowiat(String powiat) {
		this.powiat = powiat;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// @Override
	// public String toString() {
	// return "Event " + title + " w " + city + " latlng: " +
	// Double.toString(lat) + "," + lng
	// + "," + dateOfEvent;
	// }
	@Override
	public String toString() {
		return "Event " + title + " w " + city + " latlng: " + lat + "," + lng
				+ "," + dateOfEvent;
	}

}