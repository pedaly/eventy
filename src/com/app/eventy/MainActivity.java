package com.app.eventy;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
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
	private UpdatingService update;
	private EventDAO eventDao;
	private MapView mapView;
	private Location lastLocation;
	private Drawable eventMarker;
	private Bitmap myLocationMarker;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    
	    mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    settings = getSharedPreferences(PREFS_NAME, 0);
	    mapView.getController().setZoom(settings.getInt("zoom", 7));
	    context=getApplicationContext();
	    update=new UpdatingService(settings);
	    eventDao=new EventDAO(context);
	    
	    eventMarker = this.getResources().getDrawable(R.drawable.event_marker);
	    myLocationMarker = BitmapFactory.decodeResource(getResources(), R.drawable.my_location_marker);
	    		
	    locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	   
	     if(settings.getBoolean("firstRun", true)){
	    	 SharedPreferences.Editor editor = settings.edit();
	         editor.putBoolean("firstRun", false);
	         editor.commit();
	         Intent intent = new Intent(this, SettingsActivity.class);
	         startActivity(intent);
	         
	     }
	     
	     lastLocation=new Location(LocationManager.NETWORK_PROVIDER);
	     lastLocation.setLatitude(settings.getFloat("lat", 52.05f));
	     lastLocation.setLongitude(settings.getFloat("long", 19.45f));
	     
	     refreshView();
	    
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
    
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
		 menu.add(Menu.NONE,MENU_UPDATE_ID,Menu.NONE,"Poka� eventy");
	     	menu.add(Menu.NONE,MENU_SETTING_ID,Menu.NONE,"Ustawienia");
	     	menu.add(Menu.NONE,MENU_EXIT_ID,Menu.NONE,"Wyj�cie");
	        
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
	    		LocationListener locationListener = new LocationListener() {
	    		    public void onLocationChanged(Location location) {
	    		    	
	    		    	locationManager.removeUpdates(this);
	    		    	AsyncTask<Location, Void, Location> updateTask = new AsyncTask<Location, Void, Location>() {

	    		    		@Override
	    		    		protected Location doInBackground(Location... loc) {
	    		    				try {
	    	    		    		
   	    					List<Event> le=update.SendRequest(loc[0]);
	    	    				
	    	    					eventDao.deleteAllEvents();
	    	    					eventDao.saveEvents(le);
	    	    					
	    	    				
	    	    					
	    	    				} catch (IOException e) {
	    	    			
	    	    					e.printStackTrace();
	    	    				}
	    		    			
	    		    		return loc[0];
	    		    		}
	    		    		
	    		    		 @Override
	    		    		 protected void onPostExecute(Location location) {
	    		    			 lastLocation = location;
	    		    			 refreshView();
	    		    		 }
	    		    		
	    		    		


	    		    	

	    		    		};

	    		    		updateTask.execute(location, null, null);
	    	    		    	
	    		    	 
	    		    	
	    		    	
	    		    }

	    		    public void onStatusChanged(String provider, int status, Bundle extras) {}

	    		    public void onProviderEnabled(String provider) {}

	    		    public void onProviderDisabled(String provider) {}
	    		  };

	    		// Register the listener with the Location Manager to receive location updates
	    		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	    		

	    		
	    	}
	    	return false;
	    }

	

	protected void refreshView() {
	    
	    mapView.getOverlays().clear();
	    mapView.getController().setCenter(new GeoPoint((int) (lastLocation.getLatitude() * 1e6), (int) (lastLocation.getLongitude() * 1e6)));
	 
	    EventItemizedOverlay eventItemizedOverlay = new EventItemizedOverlay(eventMarker, this);
	    
	    List<Event> events = eventDao.getAllEvents();
	    for(Event event : events) {
	    	eventItemizedOverlay.addEvent(event);
	    }

		CircleOverlay circleOverlay = new CircleOverlay(this, lastLocation.getLatitude(), lastLocation.getLongitude(), settings.getInt("promien", 50) * 1000, myLocationMarker);
		mapView.getOverlays().add(circleOverlay);
		
	    if(events.size() > 0) {
	    	mapView.getOverlays().add(eventItemizedOverlay);
	    }
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("zoom", mapView.getZoomLevel());
		editor.putFloat("lat", (float) lastLocation.getLatitude());
		editor.putFloat("long", (float) lastLocation.getLongitude());
		editor.commit();
	}
	
}
