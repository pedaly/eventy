package pl.nonamesevent.controller;

import java.util.ArrayList;
import java.util.List;

public class UserContext {

	private List<String> categories = new ArrayList<String>();
	private Long lat;
	private Long lon;
	private int searchRadius;
	
	public UserContext(){
	}
	
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public Long getLat() {
		return lat;
	}
	public void setLat(Long lat) {
		this.lat = lat;
	}
	public Long getLon() {
		return lon;
	}
	public void setLon(Long lon) {
		this.lon = lon;
	}
	public int getSearchRadius() {
		return searchRadius;
	}
	public void setSearchRadius(int searchRadius) {
		this.searchRadius = searchRadius;
	}
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");
		for(String x : categories){
			stringBuilder.append(x);
			stringBuilder.append(",");
		}
		stringBuilder.append("]");
		return "{categories : " + stringBuilder  + ", " + "lat : " + lat +  ", lon : " + lon + ", searchRadius : " + searchRadius + " }";
	}
	
	
}
