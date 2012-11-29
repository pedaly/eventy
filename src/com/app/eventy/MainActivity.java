package com.app.eventy;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.app.eventy.dao.EventDAO;
import com.app.eventy.model.Event;
import com.app.eventy.service.UpdatingService;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {
	
	public static final String PREFS_NAME = "MyPrefsFile";
	public static final int MENU_UPDATE_ID = 1;
	public static final int MENU_SETTING_ID = 2;
	public static final int MENU_EXIT_ID = 3;
	private LocationManager locationManager;
	private SharedPreferences settings;
	private Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    
	    settings = getSharedPreferences(PREFS_NAME, 0);
	    context=getApplicationContext();
	     if(settings.getBoolean("firstRun", true)){
	    	 SharedPreferences.Editor editor = settings.edit();
	         editor.putBoolean("firstRun", false);
	         editor.commit();
	         Intent intent = new Intent(this, SettingsActivity.class);
	         startActivity(intent);
	         
	     }
	 
	     refreshView();
	    
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
		 menu.add(Menu.NONE,MENU_UPDATE_ID,Menu.NONE,"Poka¿ eventy");
	     	menu.add(Menu.NONE,MENU_SETTING_ID,Menu.NONE,"Ustawienia");
	     	menu.add(Menu.NONE,MENU_EXIT_ID,Menu.NONE,"Wyjœcie");
	        
	    	return true;
	    }
	 
	  @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	    	//check selected menu item
	    	if(item.getItemId() == MENU_EXIT_ID)
	    	{
	    		//close the Activity
	    		this.finish();
	    		return true;
	    	} else if(item.getItemId() == MENU_SETTING_ID){
	    		 Intent intent = new Intent(this, SettingsActivity.class);
		         startActivity(intent);
	    		return true;
	    	} else if(item.getItemId() == MENU_UPDATE_ID){
	    		getCurrentLocation();
	    		
	    	}
	    	return false;
	    }

	private void getCurrentLocation() {
		// Acquire a reference to the system Location Manager
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
		    	UpdatingService update= new UpdatingService(settings);
		    	locationManager.removeUpdates(this);
		    	try {
					List<Event> le=update.SendRequest(location);
					EventDAO eventDao=new EventDAO(context);
					eventDao.deleteAllEvents();
					eventDao.saveEvents(le);
					
					refreshView();
					
				} catch (IOException e) {
					locationManager.removeUpdates(this);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }

		    public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}
		  };

		// Register the listener with the Location Manager to receive location updates
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		
	}

	protected void refreshView() {
	    
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.getOverlays().clear();
	    
	    mapView.getController().setCenter(new GeoPoint((int) (52.05 * 1E6), (int) (19.45 * 1E6)));
	    mapView.getController().setZoom(7);
	    mapView.setBuiltInZoomControls(true);
	    
	    Drawable eventMarker = this.getResources().getDrawable(R.drawable.event_marker);
	    EventItemizedOverlay eventItemizedOverlay = new EventItemizedOverlay(eventMarker, this);
	    
	    EventDAO eventDAO = new EventDAO(getApplicationContext());
	    List<Event> events = eventDAO.getAllEvents();
	    for(Event event : events) {
	    	eventItemizedOverlay.addEvent(event);
	    }
	    
	    mapView.getOverlays().add(eventItemizedOverlay);
		
	}
}
